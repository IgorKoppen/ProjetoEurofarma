package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/eurofarma/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PatchMapping(value = "/disable/{id}")
    public EmployeeDTO disableEmployee(@PathVariable Long id) {
        return employeeService.toggleEmployeeStatus(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(name="page", defaultValue = "0") Integer page,
                                             @RequestParam(name="size", defaultValue = "10") Integer size,
                                             @RequestParam(name="direction", defaultValue = "ASC") String direction) {
        Page<EmployeeDTO> result = employeeService.findAll(page, size, direction);
        return result.getContent();
    }

    @GetMapping(value = "/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeDTO insert(@RequestBody @Valid EmployeeDTO dto) {
        return employeeService.insert(dto);
    }

    @PutMapping
    public EmployeeDTO update(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable String username,
                                            @RequestParam String password) {
        employeeService.updatePassword(username, password);
        return ResponseEntity.ok("Atualizado com sucesso");
    }

    @PatchMapping("/changePhone/{id}")
    public ResponseEntity<?> updatePhone(@PathVariable Long id,@RequestParam String password,@RequestParam String  phone) {
       employeeService.updateCellphoneNumber(id, password, phone);
       return ResponseEntity.ok("Atualizado com sucesso");
    }

    @GetMapping(value = "/findLastTrainings/{id}")
    public List<TrainingDTO> findLastTrainings(@PathVariable Long id) {
        return employeeService.findEmployeeLastTrainingsById(id);
    }

}
