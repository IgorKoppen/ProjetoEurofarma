package br.com.connectfy.EurofarmaCliente.services;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public DepartmentInfoDTO insert(DepartmentDTO departmentDTO) {
        Department department = new Department(departmentDTO);
        return toDTO(departmentRepository.save(department));
    }

    @Transactional(readOnly = true)
    public DepartmentInfoDTO findById(Long id) {
        return departmentRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<DepartmentInfoDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartmentInfoDTO update(Long id, DepartmentDTO departmentDTO) {
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

    private DepartmentInfoDTO toDTO(Department department) {
        return new DepartmentInfoDTO(department);
    }
}
