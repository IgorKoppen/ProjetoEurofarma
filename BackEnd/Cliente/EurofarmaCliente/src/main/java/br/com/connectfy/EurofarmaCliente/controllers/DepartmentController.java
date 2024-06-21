package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> insertDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
      return departmentService.insert(departmentDTO);
    }

    @GetMapping
    public List<DepartmentDTO> findAllDepartment(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO findDepartmentById(@PathVariable Long id) {
       return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateDepartment(@PathVariable Long id,
                                               @RequestBody @Valid DepartmentDTO departmentDTO) {
        return departmentService.update(id, departmentDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }

}
