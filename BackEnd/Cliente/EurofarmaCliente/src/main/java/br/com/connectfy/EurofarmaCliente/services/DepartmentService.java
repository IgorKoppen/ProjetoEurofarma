package br.com.connectfy.EurofarmaCliente.services;
import br.com.connectfy.EurofarmaCliente.dtos.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
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
    public DepartmentDTO getById(Long id) {
        return departmentRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        department.setDepartName(departmentDTO.departName());
        return toDTO(departmentRepository.save(department));
    }

    @Transactional
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<String> getAllEmployeesPhoneNumberByDepartment(List<Long> departmentIds) {
        if (departmentIds == null || departmentIds.isEmpty()) {
            return Collections.emptyList();
        }
        return departmentRepository.findAllById(departmentIds).stream()
                .flatMap(department -> department.getEmployees().stream())
                .map(Employee::getCellphoneNumber)
                .collect(Collectors.toList());
    }

    private DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getDepartName(), department.getEmployees());
    }
}
