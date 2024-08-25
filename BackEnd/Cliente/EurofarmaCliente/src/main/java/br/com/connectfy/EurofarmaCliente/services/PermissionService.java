package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import br.com.connectfy.EurofarmaCliente.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionDTO> findAll(){
      List<Permission> permissions = permissionRepository.findAll();
      List<PermissionDTO> permissionDTOs = new ArrayList<>();
      for (Permission permission : permissions) {
          permissionDTOs.add(new PermissionDTO(permission));
      }
      return permissionDTOs;
    }

    public PermissionDTO findById(Long id){
        Permission permission = permissionRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Permissão não encontrada com id: " + id ));
        return toDTO(permission);
    }

    private PermissionDTO toDTO(Permission entity){
        return new PermissionDTO(entity);
    }
}
