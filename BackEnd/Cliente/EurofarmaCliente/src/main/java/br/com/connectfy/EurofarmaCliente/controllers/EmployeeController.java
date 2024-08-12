package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.ChangePasswordDTO;
import br.com.connectfy.EurofarmaCliente.dtos.PhoneNumberUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
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
    public ResponseEntity<PagedModel<EntityModel<EmployeeInfoDTO>>> findWithPagination(@PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = "name") Pageable pageable, PagedResourcesAssembler<EmployeeInfoDTO> assembler) {
        Page<EmployeeInfoDTO> employeePage = employeeService.findWithPagination(pageable);
        PagedModel<EntityModel<EmployeeInfoDTO>> pagedModel = assembler.toModel(employeePage);
        return ResponseEntity.ok(pagedModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeInfoDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid EmployeeUpdateDTO employeeDTO) {
       EmployeeInfoDTO employee = employeeService.update(id,employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findLastTrainings/{id}")
    public ResponseEntity<List<TrainingOfEmployeeDTO>> findEmployeeTrainingById(@PathVariable Long id) {
        List<TrainingOfEmployeeDTO> dto = employeeService.findEmployeeTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/updatePassword/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
       employeeService.updatePassword(id,changePasswordDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/changePhone/{id}")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id,@RequestBody @Valid PhoneNumberUpdateDTO updateDTO) {
      employeeService.updateCellphoneNumber(id, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
       employeeService.toggleEmployeeStatus(id);
        return ResponseEntity.noContent().build();
    }
}
