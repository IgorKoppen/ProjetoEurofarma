package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeCreateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingHistoricDTO;
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
    public EmployeeInfoDTO disableEmployee(@PathVariable Long id) {
        return employeeService.toggleEmployeeStatus(id);
    }

    @GetMapping
    public List<EmployeeInfoDTO> getAllEmployees(@RequestParam(name="page", defaultValue = "0") Integer page,
                                                   @RequestParam(name="size", defaultValue = "10") Integer size,
                                                   @RequestParam(name="direction", defaultValue = "ASC") String direction) {
        Page<EmployeeInfoDTO> result = employeeService.findAll(page, size, direction);
        return result.getContent();
    }

    @GetMapping(value = "/{id}")
    public EmployeeInfoDTO findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeInfoDTO createEmployee(@RequestBody @Valid EmployeeCreateDTO employeeDTO) {
        return employeeService.create(employeeDTO);
    }

    @PutMapping
    public EmployeeInfoDTO updateEmployee(@RequestBody @Valid EmployeeCreateDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
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
    public List<TrainingHistoricDTO> findLastTrainings(@PathVariable Long id) {
        return employeeService.findLastTrainnings(id);
    }

}
