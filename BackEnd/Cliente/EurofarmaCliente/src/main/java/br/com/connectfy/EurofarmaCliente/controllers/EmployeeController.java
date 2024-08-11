package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeInfoDTO> insert(@RequestBody @Valid EmployeeInsertDTO dto) {
        EmployeeInfoDTO employeeDTO = employeeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeInfoDTO> findById(@PathVariable Long id) {
        EmployeeInfoDTO dto = employeeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PagedModel<EmployeeInfoDTO>> findWithPagination(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        PagedModel<EmployeeInfoDTO> pagedModel = employeeService.findWithPagination(page, size, direction);
        return ResponseEntity.ok(pagedModel);
    }

    @PutMapping
    public EmployeeInfoDTO update(@RequestBody @Valid EmployeeUpdateDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeInfoDTO> updatePassword(@PathVariable Long id,
                                                          @RequestParam String oldPassword,
                                                          @RequestParam String newPassword) {
        EmployeeInfoDTO dto = employeeService.updatePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/changePhone/{id}")
    public ResponseEntity<EmployeeInfoDTO> updatePhone(@PathVariable Long id, @RequestParam String password, @RequestParam String phone) {
        EmployeeInfoDTO dto = employeeService.updateCellphoneNumber(id, password, phone);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/findLastTrainings/{id}")
    public ResponseEntity<List<TrainingOfEmployeeDTO>> findEmployeeTrainingByIdWithPagination(@PathVariable Long id) {
        List<TrainingOfEmployeeDTO> dto = employeeService.findEmployeeTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/disable/{id}")
    public ResponseEntity<EmployeeInfoDTO> disable(@PathVariable Long id) {
        EmployeeInfoDTO dto = employeeService.toggleEmployeeStatus(id);
        return ResponseEntity.ok(dto);
    }

}
