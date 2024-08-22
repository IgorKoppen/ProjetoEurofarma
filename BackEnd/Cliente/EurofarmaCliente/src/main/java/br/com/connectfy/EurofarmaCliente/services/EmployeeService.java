package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.ChangePasswordDTO;
import br.com.connectfy.EurofarmaCliente.dtos.PhoneNumberUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.*;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.repositories.InstructorRepository;
import br.com.connectfy.EurofarmaCliente.util.EncryptedPassword;
import br.com.connectfy.EurofarmaCliente.util.PhoneNumberValidator;
import br.com.connectfy.EurofarmaCliente.util.RandomStringGenerator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    private final MessageService messageService;
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final InstructorRepository instructorRepository;


    public EmployeeService(EmployeeRepository employeeRepository, MessageService messageService, PermissionService permissionService, RoleService roleService, InstructorRepository instructorRepository) {
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.instructorRepository = instructorRepository;
    }

    @Transactional(readOnly = true)
    public Page<EmployeeInfoDTO> findWithPagination(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeInfoDTO::new);
    }

    @Transactional(readOnly = true)
    public EmployeeInfoDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
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
            defaultPermission.setDescription("funcionario");
            employee.addPermission(defaultPermission);
        } else {
            Set<Permission> permissions = dto.permissionsIds().stream()
                    .map(permissionId -> new Permission(permissionService.findById(permissionId)))
                    .collect(Collectors.toSet());
            employee.setPermissions(permissions);
        }

        employee.setRole(new Role(roleService.findById(dto.roleId())));

        boolean hasTrainerPermission = employee.getPermissions().stream()
                .anyMatch(permission -> "treinador".equals(permission.getDescription()));

        if (hasTrainerPermission) {
            Instructor instructor = new Instructor();
            instructor.setEmployee(employee);
            employee.setInstructor(instructor);
        }

        Employee savedEmployee = employeeRepository.save(employee);
         sendCreationEmployeeMessage(employee.getName(), employee.getUserName(), randomPassword, employee.getCellphoneNumber());

        return toDTO(savedEmployee);
    }

    @Transactional
    public EmployeeInfoDTO update(Long id, EmployeeUpdateDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
        String formattedCellPhone = removeCellPhoneFormatting(dto.cellphoneNumber());
        validatePhoneNumber(formattedCellPhone);
        employee.setCellphoneNumber(formattedCellPhone);

        Role role = new Role(roleService.findById(dto.roleId()));
        employee.setRole(role);

        Set<Permission> permissions = dto.permissionsIds().stream()
                .map(permissionId -> new Permission(permissionService.findById(permissionId)))
                .collect(Collectors.toSet());
        employee.setPermissions(permissions);
        if (employee.getInstructor() == null) {
            boolean hasTrainerPermission = permissions.stream()
                    .anyMatch(permission -> "treinador".equals(permission.getDescription()));

            if (hasTrainerPermission) {

                Instructor instructor = new Instructor();
                employee.setInstructor(instructor);
                instructorRepository.save(instructor);
            }
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return toDTO(savedEmployee);
    }

    @Transactional(readOnly = true)
    public EmployeeInfoDTO findByUserName(String username) {
        Employee entity = employeeRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com username: " + username));
        return toDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id);
        }
        try {
            employeeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }


    @Transactional
    public void updatePassword(Long id, ChangePasswordDTO dto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
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
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
        if (!EncryptedPassword.matchesPassword(dto.password(), employee.getPassword())) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        employeeRepository.changeCellphoneNumber(id, formatedCellPhone);
    }



    @Transactional
    public void toggleEmployeeStatus(Long id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
        boolean newStatus = !entity.isEnabled();
        employeeRepository.toggleEmployeeStatus(id, newStatus);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com username:" + username));
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

                        Bem-vindo(a) à Eurofarma Treinamentos! Sua conta foi criada. \
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

    private String generateUserName(String name, String surname, String cellphone) {
        surname = surname.trim();
        String[] surnameParts = surname.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : surnameParts) {
            if (!part.isEmpty()) {
                initials.append(Character.toLowerCase(part.charAt(0)));
            }
        }
        String lastFourDigits = cellphone.length() >= 4 ? cellphone.substring(cellphone.length() - 4) : cellphone;
        return name + initials + lastFourDigits;
    }


}