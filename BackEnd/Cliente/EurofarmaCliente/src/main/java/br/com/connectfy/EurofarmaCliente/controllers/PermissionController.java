package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> findAll() {
        List<PermissionDTO> permissionDTOS = permissionService.findAll();
        return ResponseEntity.ok(permissionDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> findById(@PathVariable Long id) {
        PermissionDTO permissionDTO = permissionService.findById(id);
        return ResponseEntity.ok(permissionDTO);
    }
}
