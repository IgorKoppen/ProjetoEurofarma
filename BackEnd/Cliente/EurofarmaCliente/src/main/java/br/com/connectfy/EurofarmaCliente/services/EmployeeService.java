package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExisteException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidPhoneNumberException;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDoesntMatchException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.util.GenerateEncryptedPassword;
import br.com.connectfy.EurofarmaCliente.util.PhoneNumberValidator;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.data.domain.*;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {

    private final
    EmployeeRepository employeeRepository;

    private final MessageService messageService;

    public EmployeeService(EmployeeRepository employeeRepository, MessageService messageService) {
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
    }


    @Transactional(readOnly = true)
    public PagedModel<EmployeeInfoDTO> findWithPagination(int pageNo, int pageSize, String sortDirection) {
        Sort.Direction sort = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort, "name"));
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<EmployeeInfoDTO> dtoList = employeePage.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                employeePage.getSize(),
                employeePage.getNumber(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages()
        );
        return PagedModel.of(dtoList, metadata);
    }

    @Transactional(readOnly = true)
    public EmployeeInfoDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return toDTO(employee);
    }

    @Transactional
    public EmployeeInfoDTO insert(EmployeeInsertDTO dto) {
        if(employeeRepository.existsByCellphoneNumber(dto.cellphoneNumber())){
            throw new AlreadyExisteException("O número de celular já existe!");
        }
        Employee employee = new Employee(dto);

        String formattedCellPhone = removeCellPhoneFormatting(employee.getCellphoneNumber());
        validatePhoneNumber(formattedCellPhone);
        employee.setCellphoneNumber(formattedCellPhone);
        employee.setUserName(generateUserName(employee.getName(), employee.getSurname(), employee.getCellphoneNumber()));
        String randomPassword = RandomStringGenerator.generatePassword(10);
        employee.setPassword(GenerateEncryptedPassword.encryptPassword(randomPassword));


        employee.setEnabled(true);
        employee.setAccountNonExpired(true);
        employee.setCredentialsNonExpired(true);

        Department department = new Department();
        department.setId(dto.departmentId());
        employee.setDepartment(department);
        if(dto.permissionsIds().isEmpty()){
            Permission defaultPermission = new Permission();
            defaultPermission.setId(3L);
           employee.addPermission(defaultPermission);
        }else{
            employee.addPermissionsIds(dto.permissionsIds());
        }

        Employee savedEmployee = employeeRepository.save(employee);
        //sendCreationEmployeeMessage(employee.getName(),employee.getUserName(),randomPassword,employee.getCellphoneNumber());
        return toDTO(savedEmployee);
    }

    @Transactional
    public EmployeeInfoDTO update(EmployeeUpdateDTO dto) {
        Employee employee = null;
        String formatedCellPhone = removeCellPhoneFormatting(employee.getCellphoneNumber());
        validatePhoneNumber(formatedCellPhone);
        employee.setCellphoneNumber(formatedCellPhone);
        employee.setUserName(generateUserName(employee.getName(), employee.getSurname(), employee.getCellphoneNumber()));
        return toDTO(employeeRepository.save(employee));
    }

    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            employeeRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }

    @Transactional
    public EmployeeInfoDTO updatePassword(Long id, String oldPassword, String newPassword) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (!employee.getPassword().equals(oldPassword)) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        Employee entity = employeeRepository.changePassword(id, GenerateEncryptedPassword.encryptPassword(newPassword)).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return toDTO(entity);
    }

    @Transactional
    public EmployeeInfoDTO updateCellphoneNumber(Long id, String password, String cellphoneNumber) {
        String formatedCellPhone = removeCellPhoneFormatting(cellphoneNumber);
        validatePhoneNumber(formatedCellPhone);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (!GenerateEncryptedPassword.matchesPassword(password, employee.getPassword())) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        employee.setCellphoneNumber(formatedCellPhone);
        return toDTO(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    public List<TrainingDTO> findEmployeeTrainingsById(Long id) {
        Employee entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        Comparator<EmployeeTraining> comparator = Comparator.comparing(training -> training.getTraining().getCreationDate());
        return entity.getEmployeeTrainings().stream()
                .sorted(comparator)
                .map(training -> new TrainingDTO(training.getTraining())).toList();
    }

    @Transactional
    public EmployeeInfoDTO toggleEmployeeStatus(Long id) {
        Employee entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        boolean newStatus = !entity.isEnabled();
        employeeRepository.toggleEmployeeStatus(id, newStatus);
        return toDTO(entity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("No records found with username: " + username));
    }


    private EmployeeInfoDTO toDTO(Employee employee) {
        return new EmployeeInfoDTO(employee);
    }

    private String removeCellPhoneFormatting(String cellphoneNumber) {
        String cellphoneFormat = cellphoneNumber.replaceAll("\\D", "");
        return "+" + cellphoneFormat;
    }

    private void validatePhoneNumber(String cellphoneNumber) {
        if (!PhoneNumberValidator.validatePhoneNumber(cellphoneNumber)) {
            throw new InvalidPhoneNumberException("Número de celular inválido! Deve estar no formato E.164 e conter até 15 dígitos.");
        }
    }

    private void sendCreationEmployeeMessage(String employeeName, String employeeUsername, String password, String cellphoneNumber) {
        String message = String.format(
                """
                        Olá %s!

                        Bem-vindo(a) à Eurofarma Treinamentos! Sua conta foi criada com sucesso. \
                        Seu nome de usuário é: %s
                        E sua senha temporária é: %s

                        Recomendamos que você faça o login imediatamente e altere sua senha. \
                        Caso tenha alguma dúvida, não hesite em entrar em contato conosco.

                        Estamos felizes em tê-lo(a) conosco!

                        Atenciosamente,
                        Equipe Eurofarma Treinamentos""",
                employeeName,
                employeeUsername,
                password
        );
        messageService.send(message, cellphoneNumber);
    }

    private String generateUserName(String name, String surname, String telefone) {
        surname = surname.trim();
        String[] surnameParts = surname.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : surnameParts) {
            if (!part.isEmpty()) {
                initials.append(Character.toLowerCase(part.charAt(0)));
            }
        }
        String lastFourDigits = telefone.length() >= 4 ? telefone.substring(telefone.length() - 4) : telefone;
        return name + initials + lastFourDigits;
    }
}