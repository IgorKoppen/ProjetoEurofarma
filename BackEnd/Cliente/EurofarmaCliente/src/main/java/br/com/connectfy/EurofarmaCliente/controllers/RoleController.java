package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.RoleDTO;
import br.com.connectfy.EurofarmaCliente.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<String> insertRole(@RequestBody @Valid RoleDTO instructorDTO) {
        return roleService.insert(instructorDTO);
    }
    @GetMapping
    public List<RoleDTO> findAllRoles(){
        return roleService.findAll();
    }


    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable Long id) {
        roleService.delete(id);
    }
}
