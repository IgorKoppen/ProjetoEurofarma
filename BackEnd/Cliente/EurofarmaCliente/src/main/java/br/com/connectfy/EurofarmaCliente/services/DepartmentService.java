package br.com.connectfy.EurofarmaCliente.services;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInfoDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public DepartmentDTO insert(DepartmentDTO departmentDTO) {
        Department department = new Department(departmentDTO);
        return toDTO(departmentRepository.save(department));
    }

    @Transactional(readOnly = true)
    public DepartmentDTO findById(Long id) {
        return departmentRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum departamento encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<DepartmentInfoDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(this::toInfoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum departamento encontrado com id: " + id));
        department.setDepartName(departmentDTO.getDepartName());
        return toDTO(departmentRepository.save(department));
    }

    @Transactional
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum departamento encontrado com id: " + id);
        }try {
            departmentRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public Set<String> findAllEmployeePhoneNumbersByDepartmentIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return departmentRepository.findAllById(ids)
                .stream()
                .flatMap(department -> department.getRoles().stream())
                .flatMap(role -> role.getEmployees().stream())
                .map(Employee::getCellphoneNumber)
                .collect(Collectors.toSet());
    }

    private DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department);
    }

    private DepartmentInfoDTO toInfoDTO(Department department) {
        List<RoleInfoDTO> roleInfoDTOs = department.getRoles().stream()
                .map(role -> new RoleInfoDTO(role.getId(), role.getRoleName()))
                .collect(Collectors.toList());

        DepartmentInfoDTO dto = new DepartmentInfoDTO();
        dto.setId(department.getId());
        dto.setDepartName(department.getDepartName());
        dto.setRoles(roleInfoDTOs);

        return dto;
    }

}
