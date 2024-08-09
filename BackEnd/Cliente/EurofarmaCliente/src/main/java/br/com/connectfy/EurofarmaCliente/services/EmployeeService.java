package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.InvalidPhoneNumberException;
import br.com.connectfy.EurofarmaCliente.exceptions.PasswordDoesntMatchException;
import br.com.connectfy.EurofarmaCliente.exceptions.RequiredObjectIsNullException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.util.GenerateEncryptedPassword;
import org.springframework.data.domain.*;
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


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeDTO toggleEmployeeStatus(Long id) {
        Employee entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        boolean newStatus = !entity.isEnabled();
        employeeRepository.toggleEmployeeStatus(id, newStatus);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Integer pageNo, Integer pageSize, String sortDirection) {
        Sort.Direction sort = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort, "name"));
        Page<Employee> list = employeeRepository.findAll(pageable);
        List<EmployeeDTO> dtoList = list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, list.getTotalElements());
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return toDTO(employee);
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee employee = new Employee(dto);
        String formatedCellPhone = removeCellPhoneFormatting(employee.getCellphoneNumber());
        validatePhoneNumber(formatedCellPhone);
        employee.setCellphoneNumber(formatedCellPhone);
        employee.setUserName(generateUserName(employee.getName(), employee.getSurname(), employee.getCellphoneNumber()));
        return toDTO(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeDTO update(EmployeeDTO dto) {
        if (dto == null) throw new RequiredObjectIsNullException();
        try {
            var entity = findById(dto.getId());
            Employee employee = new Employee(entity);
            String formatedCellPhone = removeCellPhoneFormatting(employee.getCellphoneNumber());
            validatePhoneNumber(formatedCellPhone);
            employee = employeeRepository.save(employee);
            return toDTO(employee);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + dto.getId());
        }
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
    public void updatePassword(String username, String newPassword) {
        try {
            var entity = employeeRepository.findByUsername(username);
            employeeRepository.changePassword(entity.getId(), GenerateEncryptedPassword.encryptPassword(newPassword));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with username: " + username);
        }
    }

    @Transactional
    public void updateCellphoneNumber(Long id, String password, String cellphoneNumber) {
        String formatedCellPhone = removeCellPhoneFormatting(cellphoneNumber);
        validatePhoneNumber(formatedCellPhone);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        if (!GenerateEncryptedPassword.matchesPassword(password, employee.getPassword())) {
            throw new PasswordDoesntMatchException("Senha incorreta!");
        }
        employee.setCellphoneNumber(formatedCellPhone);
        employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<TrainingDTO> findEmployeeLastTrainingsById(Long id) {
        Employee entity = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        Comparator<EmployeeTraining> comparator = Comparator.comparing(training -> training.getTraining().getCreationDate());
        return entity.getEmployeeTrainings().stream()
                .sorted(comparator)
                .map(training -> new TrainingDTO(training.getTraining())).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = employeeRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }



    private EmployeeDTO toDTO(Employee employee) {
        Long instructorId = null;
        if (employee.getInstructor() != null) {
            instructorId = employee.getInstructor().getId();
        }
        return new EmployeeDTO(employee);
    }

    private String removeCellPhoneFormatting(String cellphoneNumber) {
        return cellphoneNumber.replaceAll("[^\\d]", "");
    }

    private void validatePhoneNumber(String cellphoneNumber) {
        if (cellphoneNumber.length() != 11) {
            throw new InvalidPhoneNumberException("Número de celular inválido! Deve conter 11 dígitos.");
        }
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