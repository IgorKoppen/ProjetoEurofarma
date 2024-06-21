package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainningHistoricDTO;
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

    @PatchMapping(value = "/{id}")
    public EmployeeDTO disableEmployee(@PathVariable Long id) {
        return employeeService.disablePerson(id);
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
    public EmployeeDTO createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.create(employeeDTO);
    }

    @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> updatePassword(@RequestBody @Valid EmployeeDTO employeeDTO,
                                            @RequestBody @Valid String password) {
        employeeService.updatePassword(employeeDTO, password);
        return ResponseEntity.ok("Atualizado com sucesso");
    }

    @GetMapping(value = "/findLastTrainings")
    public List<TrainningHistoricDTO> findLastTrainings(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.findLastTrainnings(employeeDTO);
    }

}
