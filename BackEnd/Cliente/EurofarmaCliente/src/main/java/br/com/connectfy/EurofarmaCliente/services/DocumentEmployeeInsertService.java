package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeRegistrationResponseDTO;
import br.com.connectfy.EurofarmaCliente.dtos.EmployeeProcessingDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidDataException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidFileException;
import br.com.connectfy.EurofarmaCliente.exceptions.ProcessRowDataException;
import br.com.connectfy.EurofarmaCliente.models.*;
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
public class DocumentEmployeeInsertService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final MessageService messageService;

    public DocumentEmployeeInsertService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository, MessageService messageService) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.messageService = messageService;
    }

        @Transactional
        public EmployeeRegistrationResponseDTO readExcelFile(MultipartFile file) throws IOException {
            validateFileType(file);
            List<EmployeeProcessingDTO> errors = new LinkedList<>();
            List<ProcessedEmployeeResult> processedResults = processExcelFile(file,errors);
            List<Employee> employeeList = new LinkedList<>();


            for (ProcessedEmployeeResult result : processedResults) {
                Employee employee = result.getEmployee();
                int rowIndex = result.getRowIndex();

                try {
                    if (employeeRepository.existsByEmployeeRegistration(employee.getEmployeeRegistration())) {
                        continue;
                    }
                    if (employeeRepository.existsByCellphoneNumber(employee.getCellphoneNumber())) {
                        errors.add(new EmployeeProcessingDTO("O número de celular " + employee.getCellphoneNumber() + " já existe.", rowIndex + 1));
                        continue;
                    }
                    String rawPassword = RandomStringGenerator.generatePassword(10);
                    setDefaultEmployeeSettings(employee, rawPassword);
                    employee = employeeRepository.save(employee);
                    employeeList.add(employee);
                    sendCreationEmployeeMessage(employee.getName(), employee.getEmployeeRegistration().toString(), rawPassword, employee.getCellphoneNumber());
                } catch (RuntimeException e) {
                    errors.add(new EmployeeProcessingDTO(e.getMessage(), rowIndex ));
                }
            }

            return new EmployeeRegistrationResponseDTO(employeeList.size(), errors);
        }

        private void validateFileType(MultipartFile file) throws InvalidDataException {
            if (!isExcelFile(file)) {
                throw new InvalidFileException("Arquivo inválido. Somente arquivos Excel são aceitos.");
            }
        }

        private boolean isExcelFile(MultipartFile file) {
            String contentType = file.getContentType();
            if(contentType == null){
                return false;
            }
            return contentType.equals("application/vnd.ms-excel") || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }

        private List<ProcessedEmployeeResult> processExcelFile(MultipartFile file,List<EmployeeProcessingDTO> errors) throws IOException {
            List<ProcessedEmployeeResult> employeesWithIndices;
            Workbook workbook = null;
            String uploadDir = "src/main/resources/EmployeeDocuments";
            Path destinationPath = null;

            try {
                destinationPath = saveFile(file, uploadDir);
                workbook = WorkbookFactory.create(destinationPath.toFile());
                employeesWithIndices = processWorkbook(workbook,errors);
            } catch (EncryptedDocumentException | IOException e) {
                throw new InvalidFileException("Erro ao abrir o arquivo: " + e.getMessage());
            } finally {
                if (workbook != null) {
                    workbook.close();
                }
                if (destinationPath != null) {
                    Files.delete(destinationPath);
                }
            }

            return employeesWithIndices;
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
                throw new IOException("Arquivo não salvo corretamente");
            }

            return destinationPath;
        }

    private List<ProcessedEmployeeResult> processWorkbook(Workbook workbook, List<EmployeeProcessingDTO> errors) {
        List<ProcessedEmployeeResult> employeesWithIndices = new LinkedList<>();

        for (Sheet sheet : workbook) {
            int index = 0;

            for (Row row : sheet) {
                if (index++ == 0) continue;

                if (isRowEmpty(row)) continue;

                try {
                    ProcessedEmployeeResult result = processRow(row);
                    employeesWithIndices.add(result);
                } catch (ProcessRowDataException e) {
                    errors.add(new EmployeeProcessingDTO(e.getMessage(), index));
                }
            }
        }
        return employeesWithIndices;
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private ProcessedEmployeeResult processRow(Row row) throws ProcessRowDataException {
        Employee employee = new Employee();
        Department department;
        int rowIndex = row.getRowNum();

        Long employeeRegistration = getNumericCellValue(row); // Get registration
        employee.setEmployeeRegistration(employeeRegistration);

        String formattedCellphoneNumber = formatCellphoneNumber(row);
        employee.setCellphoneNumber(formattedCellphoneNumber);

        employee.setName(getStringCellValue(row, 1, "Nome nulo ou sem ser texto")); // Get name
        employee.setSurname(getStringCellValue(row, 2, "Sobrenome nulo ou sem ser texto")); // Get surname

        department = getOrCreateDepartmentFromCell(row.getCell(4)); // Get or create department
        employee.setRole(getOrCreateRoleFromCell(row.getCell(5), department)); // Get or create role

        return new ProcessedEmployeeResult(employee, rowIndex);
    }

    private Long getNumericCellValue(Row row) throws ProcessRowDataException {

            if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
                return Double.valueOf(row.getCell(0).getNumericCellValue()).longValue();
            } else{
                throw new ProcessRowDataException("Registro de funcionário não numérico ou ausente");
            }

    }

    private String getStringCellValue(Row row, int cellIndex, String errorMessage) throws ProcessRowDataException {
        if (row.getCell(cellIndex) != null && row.getCell(cellIndex).getCellType() == CellType.STRING) {
            return row.getCell(cellIndex).getStringCellValue();
        }
        throw new ProcessRowDataException(errorMessage);
    }

    private String formatCellphoneNumber(Row row) throws ProcessRowDataException {
        if (row.getCell(3) != null) {
            if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                BigDecimal cellphoneNumber = BigDecimal.valueOf(row.getCell(3).getNumericCellValue());
                return "+" + cellphoneNumber.toPlainString();
            } else if (row.getCell(3).getCellType() == CellType.STRING) {
                return row.getCell(3).getStringCellValue();
            }
        }
        throw new ProcessRowDataException("Número de telefone inválido ou ausente");
    }

    private Department getOrCreateDepartmentFromCell(Cell cell) throws ProcessRowDataException {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String departmentName = cell.getStringCellValue();
            return departmentRepository.findByDepartNameIgnoreCase(departmentName)
                    .orElseGet(() -> departmentRepository.save(new Department(departmentName)));
        }
        throw new ProcessRowDataException("Departamento nulo ou sem ser texto");
    }

    private Role getOrCreateRoleFromCell(Cell cell, Department department) throws ProcessRowDataException {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String roleName = cell.getStringCellValue();
            return roleRepository.findByRoleNameAndDepartment(roleName, department)
                    .orElseGet(() -> roleRepository.save(new Role(roleName, department)));
        }
        throw new ProcessRowDataException("Cargo nulo ou sem ser texto");
    }

        private void setDefaultEmployeeSettings(Employee employee, String rawPassword) {
            employee.setPassword(EncryptedPassword.encryptPassword(rawPassword));
            employee.setEnabled(true);
            employee.setAccountNonExpired(true);
            employee.setCredentialsNonExpired(true);
            employee.setAccountNonLocked(true);

            Permission defaultPermission = new Permission();
            defaultPermission.setId(3L);
            defaultPermission.setDescription("Employee");
            employee.addPermission(defaultPermission);
        }

        private void sendCreationEmployeeMessage(String employeeName, String registerOfEmployee, String password, String cellphoneNumber) {
            String message = String.format("""
            Olá %s! Bem-vindo(a) à Eurofarma Treinamentos!
            Sua conta foi criada com sucesso.
            Use o seu Registro de Funcionário como nome de usuário: %s.
            Sua senha temporária é: %s.
            Recomendamos que você faça login imediatamente e altere sua senha para garantir a segurança da sua conta.

            Se tiver alguma dúvida, não hesite em entrar em contato conosco.
            Estamos felizes em tê-lo(a) conosco!

            Atenciosamente,
            Equipe Eurofarma
            """, employeeName, registerOfEmployee, password);

            messageService.send(message, cellphoneNumber);
        }
    }

