package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExistException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidDataException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.repositories.RoleRepository;
import br.com.connectfy.EurofarmaCliente.util.EncryptedPassword;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

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
        validateFileType(file); // Valida se o arquivo é do tipo correto

        List<Employee> employees;
        if (isExcelFile(file)) {
            employees = processExcelFile(file);
        } else {
            throw new InvalidDataException("Formato de arquivo não suportado. Apenas arquivos Excel (.xls, .xlsx) são aceitos.");
        }

        // Salvar todos os funcionários processados
        List<Employee> employeeList = employeeRepository.saveAll(employees);
        return employeeList.stream().map(EmployeeInfoDTO::new).toList();
    }

    private void validateFileType(MultipartFile file) throws InvalidDataException {
        String contentType = file.getContentType();
        if (!isExcelFile(file)) {
            throw new InvalidDataException("Arquivo inválido. Somente arquivos Excel são aceitos.");
        }
    }

    private boolean isExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.ms-excel") ||
                contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    private List<Employee> processExcelFile(MultipartFile file) throws IOException {
        List<Employee> employees = new LinkedList<>();
        Workbook workbook = null;
        String uploadDir = "src/main/resources/EmployeeDocuments";

        try {
            Path destinationPath = saveFile(file, uploadDir);
            workbook = WorkbookFactory.create(destinationPath.toFile());
            employees = processWorkbook(workbook);
        } catch (EncryptedDocumentException | IOException e) {
            throw new AlreadyExistException("Erro ao abrir arquivo: " + e.getMessage());
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return employees;
    }

    private Path saveFile(MultipartFile file, String uploadDir) throws IOException {
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

        return destinationPath;
    }

    private List<Employee> processWorkbook(Workbook workbook) {
        List<Employee> employees = new LinkedList<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (Sheet sheet : workbook) {
            int index = 0;
            for (Row row : sheet) {
                if (index++ == 0) continue; // Ignora cabeçalho

                Employee employee = processRow(row, dataFormatter);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    private Employee processRow(Row row, DataFormatter dataFormatter) {
        Employee employee = new Employee();
        Department department = new Department();
        Role role = new Role();

        try {
            // Verifica se o employeeRegistration já existe no banco de dados
            Long employeeRegistration = getNumericCellValue(row, 0, "RE nulo ou não numérico");
            if (employeeRepository.existsByEmployeeRegistration(employeeRegistration)) {
                throw new AlreadyExistException("O registro de funcionário " + employeeRegistration + " já existe.");
            }
            employee.setEmployeeRegistration(employeeRegistration);

            // Verifica se o número de telefone já existe no banco de dados
            String formattedCellphoneNumber = formatCellphoneNumber(row);
            if (employeeRepository.existsByCellphoneNumber(formattedCellphoneNumber)) {
                throw new AlreadyExistException("O número de telefone " + formattedCellphoneNumber + " já está registrado.");
            }
            employee.setCellphoneNumber(formattedCellphoneNumber);

            employee.setName(getStringCellValue(row, 1, "Nome nulo ou numérico"));
            employee.setSurname(getStringCellValue(row, 2, "Sobrenome nulo ou numérico"));

            department = getOrCreateDepartmentFromCell(row.getCell(4)); // Para Excel, usa Cell
            employee.setRole(getOrCreateRoleFromCell(row.getCell(5), department)); // Para Excel, usa Cell

            setDefaultEmployeeSettings(employee);
            return employee;
        } catch (InvalidDataException | AlreadyExistException e) {
            // Log de erro ou manipulação de exceção
            return null;
        }
    }

    private Long getNumericCellValue(Row row, int cellIndex, String errorMessage) throws InvalidDataException {
        if (row.getCell(cellIndex) != null && row.getCell(cellIndex).getCellType() == CellType.NUMERIC) {
            return (long) row.getCell(cellIndex).getNumericCellValue();
        }
        throw new InvalidDataException(errorMessage);
    }

    private String getStringCellValue(Row row, int cellIndex, String errorMessage) throws InvalidDataException {
        if (row.getCell(cellIndex) != null && row.getCell(cellIndex).getCellType() == CellType.STRING) {
            return row.getCell(cellIndex).getStringCellValue();
        }
        throw new InvalidDataException(errorMessage);
    }

    private String formatCellphoneNumber(Row row) throws InvalidDataException {
        if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
            BigDecimal cellphoneNumber = BigDecimal.valueOf(row.getCell(3).getNumericCellValue());
            String formattedCellphoneNumber = "+" + cellphoneNumber.toPlainString();
            return formattedCellphoneNumber;
        } else {
            throw new InvalidDataException("Celular nulo ou não numérico");
        }
    }

    // Para arquivos Excel (usa Cell)
    private Department getOrCreateDepartmentFromCell(Cell cell) throws InvalidDataException {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String departmentName = cell.getStringCellValue();
            return departmentRepository.findByDepartNameIgnoreCase(departmentName)
                    .orElseGet(() -> departmentRepository.save(new Department(departmentName)));
        }
        throw new InvalidDataException("Departamento nulo ou numérico");
    }

    // Para arquivos Excel (usa Cell)
    private Role getOrCreateRoleFromCell(Cell cell, Department department) throws InvalidDataException {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String roleName = cell.getStringCellValue();
            return roleRepository.findByRoleNameAndDepartment(roleName, department)
                    .orElseGet(() -> roleRepository.save(new Role(roleName, department)));
        }
        throw new InvalidDataException("Cargo nulo ou numérico");
    }

    private void setDefaultEmployeeSettings(Employee employee) {
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
    }

}
