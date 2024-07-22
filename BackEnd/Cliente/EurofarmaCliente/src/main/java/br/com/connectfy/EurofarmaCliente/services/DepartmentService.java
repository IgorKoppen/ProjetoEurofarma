package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> insert(DepartmentDTO departmentDTO) {
      Department  department = new Department(departmentDTO);
      departmentRepository.save(department);
      return ResponseEntity.ok("Departamento inserido com sucesso!");
    }

    @Transactional(readOnly = true)
    public DepartmentDTO getById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
        return new DepartmentDTO(department.getId(), department.getDepartName(), department.getEmployees());
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
       List<Department> departments = departmentRepository.findAll();
       return departments.stream().map(department
               -> new DepartmentDTO(department.getId(),
               department.getDepartName(),department.getEmployees()))
               .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<String> update(Long id, DepartmentDTO departmentDTO) {
       Department updateDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found with id: " + id));
       updateDepartment.setDepartName(departmentDTO.departName());
       departmentRepository.save(updateDepartment);
       return ResponseEntity.ok("Departamento atualizado com sucesso!");
    }

    @Transactional
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            departmentRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }
}
