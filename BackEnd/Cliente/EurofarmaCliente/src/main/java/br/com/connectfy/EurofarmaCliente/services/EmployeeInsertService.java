package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExistException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidDataException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;

import br.com.connectfy.EurofarmaCliente.repositories.RoleRepository;
import br.com.connectfy.EurofarmaCliente.util.EncryptedPassword;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeInsertService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public EmployeeInsertService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public List<EmployeeInfoDTO> readExcelFile(MultipartFile file) throws IOException {
        List<Employee> employees = new LinkedList<>();
        List<Employee> employeeList = new LinkedList<>();
        Workbook workbook = null;
        String uploadDir = "src/main/resources/EmployeeDocuments";

        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            String originalFilename = file.getOriginalFilename();
            Path destinationPath = path.resolve(originalFilename);

            try (FileOutputStream fos = new FileOutputStream(destinationPath.toFile())) {
                IOUtils.copy(file.getInputStream(), fos);
            }

            if (!Files.exists(destinationPath)) {
                throw new IOException("O arquivo não foi salvo corretamente.");
            }

            workbook = WorkbookFactory.create(destinationPath.toFile());

            for (Sheet sheet : workbook) {
                DataFormatter dataFormatter = new DataFormatter();
                int index = 0;
                for (Row row : sheet) {
                    if (index++ == 0) continue;

                    Employee employee = new Employee();
                    Department department = new Department();
                    Role role = new Role();

                    // Implementação original do processamento das linhas
                    // RE
                    if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
                        if (employeeRepository.existsByEmployeeRegistration((long) row.getCell(0).getNumericCellValue())) {
                            continue;
                        }
                        employee.setEmployeeRegistration((long) row.getCell(0).getNumericCellValue());
                    }
                    else throw new InvalidDataException("RE nulo ou não numérico");
                    // Name
                    if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                        employee.setName(row.getCell(1).getStringCellValue());
                    }
                    else throw new InvalidDataException("Nome nulo ou numérico");
                    // Surname
                    if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                        employee.setSurname(row.getCell(2).getStringCellValue());
                    }
                    else throw new InvalidDataException("Sobrenome nulo ou numérico");
                    if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
                        BigDecimal cellphoneNumber = new BigDecimal(row.getCell(3).getNumericCellValue());
                        String formattedCellphoneNumber = "+" + cellphoneNumber.toPlainString();
                        if (employeeRepository.existsByCellphoneNumber(formattedCellphoneNumber)) {
                            continue;
                        }
                        employee.setCellphoneNumber(formattedCellphoneNumber);
                    }
                    else throw new InvalidDataException("Celular nulo ou não numérico");
                    if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                        Optional<Department> optionalDepartment = departmentRepository.findByDepartNameIgnoreCase(row.getCell(4).getStringCellValue());
                        if (optionalDepartment.isEmpty()) {
                            department.setDepartName(row.getCell(4).getStringCellValue());
                            department = departmentRepository.save(department);
                        } else {
                            department = optionalDepartment.orElseThrow(() -> new ResourceNotFoundException("Department " + row.getCell(4).getStringCellValue()));
                        }
                    }
                    else throw new InvalidDataException("Departamento nulo ou numérico");
                    // Garanta inicialização de `roles`
                    Hibernate.initialize(department.getRoles());

                    if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                        Optional<Role> optionalRole = roleRepository.findByRoleNameAndDepartment(row.getCell(5).getStringCellValue(), department);
                        if (optionalRole.isEmpty()) {
                            role.setRoleName(row.getCell(5).getStringCellValue());
                            role.setDepartment(department);

                            role = roleRepository.save(role); // Salve o `Role`
                        } else {
                            Department finalDepartment = department;
                            role = optionalRole.orElseThrow(() -> new ResourceNotFoundException("Role " + row.getCell(5).getStringCellValue() + " in Department " + finalDepartment.getDepartName()));
                        }

                        employee.setRole(role); // Assigne o `Role` ao `Employee`
                    }
                    else throw new InvalidDataException("Cargo nulo ou numérico");

                    String randomPassword = RandomStringGenerator.generatePassword(10);
                    employee.setPassword(EncryptedPassword.encryptPassword(randomPassword));
                    employee.setEnabled(true);
                    employee.setAccountNonExpired(true);
                    employee.setCredentialsNonExpired(true);
                    employee.setAccountNonLocked(true);

                    Permission defaultPermission = new Permission();
                    defaultPermission.setId(3L);
                    defaultPermission.setDescription("funcionario");
                    employee.addPermission(defaultPermission);

                    employees.add(employee);
                }
            }

            // Agora salve todos os `Employee` juntos
            employeeList = employeeRepository.saveAll(employees);

        } catch (EncryptedDocumentException | IOException e) {
            throw new AlreadyExistException("Erro ao abrir arquivo: " + e.getMessage());
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        return employeeList.stream().map(EmployeeInfoDTO::new).toList();
    }
}
