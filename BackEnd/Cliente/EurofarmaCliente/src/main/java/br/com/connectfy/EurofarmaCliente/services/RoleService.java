package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.RoleDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role
                        -> new RoleDTO(role.getId(),
                        role.getRoleName(), role.getEmployee()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<String> insert(RoleDTO roleDTO) {
        Role role = new Role(roleDTO.roleName());
        roleRepository.save(role);
        return ResponseEntity.ok("Role inserida com sucesso!");
    }

    @Transactional
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
        try {
            roleRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No records found with id: " + id);
        }
    }
}
