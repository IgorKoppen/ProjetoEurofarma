package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody @Valid RoleDTO dto) {
        RoleDTO roleDTO = roleService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDTO);
    }
    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(){
        List<RoleDTO> rolesDTO = roleService.findAll();
        return ResponseEntity.ok(rolesDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
