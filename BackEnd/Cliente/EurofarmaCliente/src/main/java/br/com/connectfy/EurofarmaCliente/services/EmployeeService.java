package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeCreateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingHistoricDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.RequiredObjectIsNullException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Training;
import br.com.connectfy.EurofarmaCliente.repositories.EmployeeRepository;
import br.com.connectfy.EurofarmaCliente.repositories.TrainningRepository;
import br.com.connectfy.EurofarmaCliente.util.GenerateEncryptedPassword;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    EmployeeRepository repository;

    @Autowired
    TrainningRepository trainningRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EmployeeInfoDTO toggleEmployeeStatus(Long id) {
        Employee entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        boolean newStatus = !entity.isEnabled();
        repository.toggleEmployeeStatus(id,newStatus);
        entity.setEnabled(newStatus);
        return objToDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeInfoDTO> findAll(Integer pageNo, Integer pageSize, String sortDirection) {
        Sort.Direction sort = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort, "name"));
        Page<Employee> list = repository.findAll(pageable);
        List<EmployeeInfoDTO> dtoList = list.stream()
                .map(this::objToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, list.getTotalElements());
    }
    @Transactional(readOnly = true)
    public EmployeeInfoDTO findById(Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return objToDTO(employee);
    }
    @Transactional
    public EmployeeInfoDTO create(EmployeeCreateDTO dto) {
        Employee employee = new Employee(dto);
        employee.setUserName(generateUserName(employee.getName(),employee.getSurname(),employee.getCellphoneNumber()));
        return objToDTO(repository.save(employee));
    }
    @Transactional
    public EmployeeInfoDTO update(EmployeeCreateDTO dto) {
        if (dto == null) throw new RequiredObjectIsNullException();
        try {
            var entity = findById(dto.id());
            Employee employee = new Employee(entity);
            employee = repository.save(employee);
            return objToDTO(employee);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + dto.id());
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }

    @Transactional
    public void updatePassword(String username, String newPassword) {
        try {
            var entity = repository.findByUsername(username);
            repository.changePassword(entity.getId(), GenerateEncryptedPassword.encryptPassword(newPassword));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with username: " + username);
        }
    }

    @Transactional(readOnly = true)
    public List<TrainingHistoricDTO> findLastTrainnings(Long id) {
            Employee entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
            return entity.getTrainnings().stream().sorted(Comparator.comparing(Training::getClosingDate).reversed()
                    ).map(trainning
                            ->  new TrainingHistoricDTO(
                            trainning.getId(),
                            trainning.getName(),
                            trainning.getCode(),
                            trainning.getCreationDate(),
                            trainning.getClosingDate(),
                            trainning.getStatus(),
                            trainning.getPassword(),
                            trainning.getDescription(),
                            trainning.getInstructors().stream().map(instructor -> instructor.getEmployee().getName()).collect(Collectors.toList()),
                            trainning.getTags(),
                            null))
                    .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }

    private EmployeeInfoDTO objToDTO(Employee employee){
        Long instructorId = null;
        if (employee.getInstructor() != null) {
            instructorId = employee.getInstructor().getId();
        }
        return new EmployeeInfoDTO(
                employee.getId(),
                employee.getUsername(),
                employee.getName(),
                employee.getSurname(),
                employee.getCellphoneNumber(),
                employee.getList(),
                employee.getDepartments(),
                employee.getTrainnings(),
                instructorId);
    }

   private String generateUserName(String name,String surname,String telefone) {
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