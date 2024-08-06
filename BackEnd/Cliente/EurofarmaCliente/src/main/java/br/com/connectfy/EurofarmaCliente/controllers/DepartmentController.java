package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> insert(@RequestBody @Valid DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departmentDTO.id()).toUri();
        return ResponseEntity.created(uri).body(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        List<DepartmentDTO> departmentDTOS = departmentService.findAll();
        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.getById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id,
                                                @RequestBody @Valid DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.update(id, dto);
        return ResponseEntity.ok(departmentDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
