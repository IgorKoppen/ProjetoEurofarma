package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInsertDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final DepartmentService departmentService;


    public RoleService(RoleRepository roleRepository, DepartmentService departmentService) {
        this.roleRepository = roleRepository;
        this.departmentService = departmentService;

    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoleDTO insert(RoleInsertDTO roleDTO) {
        DepartmentInfoDTO departmentDTO = departmentService.findById(roleDTO.departmentId());
        Role role = new Role();
        role.setRoleName(roleDTO.name());
        role.setDepartment(new Department(departmentDTO));
        return toDTO(roleRepository.save(role));
    }

    @Transactional
    public RoleDTO update(Long id, RoleInsertDTO roleInsertDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário encontrado com id: " + id));
        role.setRoleName(roleInsertDTO.name());
        return toDTO(roleRepository.save(role));
    }

   @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrada com id: " + id));;
        return toDTO(role);
   }


    @Transactional
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo não encontrada com id: " + id);
        }
        try {
            roleRepository.deleteById(id);
        } catch (DataIntegrityViolationException  e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }
    private RoleDTO toDTO(Role entity){
        return new RoleDTO(entity);
    }
}
