package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.ChangePasswordDTO;
import br.com.connectfy.EurofarmaCliente.dtos.PhoneNumberUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.AlreadyExistException;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidPhoneNumberException;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDoesntMatchException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.util.EncryptedPassword;
import br.com.connectfy.EurofarmaCliente.util.PhoneNumberValidator;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {

    private final
    EmployeeRepository employeeRepository;

    private final MessageService messageService;
    private final PermissionService permissionService;
    private final RoleService roleService;


    public EmployeeService(EmployeeRepository employeeRepository, MessageService messageService, PermissionService permissionService, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public Page<EmployeeInfoDTO> findWithPagination(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeInfoDTO::new);
    }

    @Transactional(readOnly = true)
    public EmployeeInfoDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return toDTO(employee);
    }

    @Transactional
    public EmployeeInfoDTO insert(EmployeeInsertDTO dto) {
        if (employeeRepository.existsByCellphoneNumber(dto.cellphoneNumber())) {
            throw new AlreadyExistException("O número de celular já está vinculado a uma conta!");
        }
        Employee employee = new Employee(dto);

        String formattedCellPhone = removeCellPhoneFormatting(employee.getCellphoneNumber());
        validatePhoneNumber(formattedCellPhone);
        employee.setCellphoneNumber(formattedCellPhone);
        employee.setUserName(generateUserName(employee.getName(), employee.getSurname(), employee.getCellphoneNumber()));
        if (employeeRepository.existsByUserName(employee.getUsername())) {
            throw new AlreadyExistException("Usuário já cadastrado!");
        }
        String randomPassword = RandomStringGenerator.generatePassword(10);
        employee.setPassword(EncryptedPassword.encryptPassword(randomPassword));


        employee.setEnabled(true);
        employee.setAccountNonExpired(true);
        employee.setCredentialsNonExpired(true);


        if (dto.permissionsIds().isEmpty()) {
            Permission defaultPermission = new Permission();
            defaultPermission.setId(3L);
            defaultPermission.setDescription("funcionário");
            employee.addPermission(defaultPermission);
        } else {
            List<Permission> permissions = new ArrayList<>();
            for (Long permissionId : dto.permissionsIds()) {
                PermissionDTO permissionDTO = permissionService.findById(permissionId);
                permissions.add(new Permission(permissionDTO));
            }
            employee.setPermissions(permissions);
        }

        employee.setRole(new Role(roleService.findById(dto.roleId())));
        Employee savedEmployee = employeeRepository.save(employee);
        //sendCreationEmployeeMessage(employee.getName(),employee.getUserName(),randomPassword,employee.getCellphoneNumber());
        return toDTO(savedEmployee);
    }

    @Transactional
    public EmployeeInfoDTO update(Long id, EmployeeUpdateDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        String formattedCellPhone = removeCellPhoneFormatting(dto.cellphoneNumber());
        validatePhoneNumber(formattedCellPhone);
        employee.setCellphoneNumber(formattedCellPhone);

        Role role = new Role(roleService.findById(dto.roleId()));
        employee.setRole(role);

        List<Permission> permissions = dto.permissionsIds().stream()
                .map(permissionId -> new Permission(permissionService.findById(permissionId)))
                .collect(Collectors.toList());
        employee.setPermissions(permissions);

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
    public void updatePassword(Long id, ChangePasswordDTO dto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (!EncryptedPassword.matchesPassword(dto.oldPassword(), employee.getPassword())) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        employeeRepository.changePassword(id, EncryptedPassword.encryptPassword(dto.newPassword()));
    }

    @Transactional
    public void updateCellphoneNumber(Long id, PhoneNumberUpdateDTO dto) {
        String formatedCellPhone = removeCellPhoneFormatting(dto.phoneNumber());
        validatePhoneNumber(formatedCellPhone);
        if (employeeRepository.existsByCellphoneNumber(formatedCellPhone)) {
            throw new AlreadyExistException("O número de celular já está vinculado a uma conta!");
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (!EncryptedPassword.matchesPassword(dto.password(), employee.getPassword())) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        employeeRepository.changeCellphoneNumber(id, formatedCellPhone);
    }

    @Transactional(readOnly = true)
    public List<TrainingOfEmployeeDTO> findEmployeeTrainingsById(Long id) {
        Employee entity = employeeRepository.findByIdWithTrainingsSortedByClosingDate(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado funcionário com id: " + id));

        return entity.getEmployeeTrainings().stream().map(employeeTraining -> new TrainingOfEmployeeDTO(employeeTraining.getTraining())).toList();
    }

    @Transactional
    public void toggleEmployeeStatus(Long id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        boolean newStatus = !entity.isEnabled();
        employeeRepository.toggleEmployeeStatus(id, newStatus);
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