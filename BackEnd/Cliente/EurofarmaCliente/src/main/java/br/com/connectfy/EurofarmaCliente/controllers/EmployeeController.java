package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.ChangePasswordDTO;
import br.com.connectfy.EurofarmaCliente.dtos.PhoneNumberUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/eurofarma/employee")
@Tag(name = "Employee", description = "Controller Employee")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Operation(summary = "Cria um funcionário", description = "Cria um novo funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    @PostMapping(produces ="application/json")
    public ResponseEntity<EmployeeInfoDTO> insert(@RequestBody @Valid EmployeeInsertDTO dto) {
        EmployeeInfoDTO employeeDTO = employeeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeDTO);
    }


    @Operation(summary = "Consulta um funcionário", description = "Retorna um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeInfoDTO> findById(@PathVariable Long id) {
        EmployeeInfoDTO dto = employeeService.findById(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "Consulta funcionários com paginação", description = "Retorna funcionários com paginação",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/pagination", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<EmployeeInfoDTO>>> findWithPagination(@PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = "name") Pageable pageable, PagedResourcesAssembler<EmployeeInfoDTO> assembler) {
        Page<EmployeeInfoDTO> employeePage = employeeService.findWithPagination(pageable);
        PagedModel<EntityModel<EmployeeInfoDTO>> pagedModel = assembler.toModel(employeePage);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Atualiza um funcionário", description = "Atualiza um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<EmployeeInfoDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid EmployeeUpdateDTO employeeDTO) {
       EmployeeInfoDTO employee = employeeService.update(id,employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Exclui um funcionário", description = "Exclui um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Atualiza a senha de um funcionário", description = "Atualiza a senha de um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PatchMapping(value = "/updatePassword/{id}", produces ="application/json")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
       employeeService.updatePassword(id,changePasswordDTO);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Atualiza o telefone de um funcionário", description = "Atualiza o número de telefone de um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PatchMapping(value = "/changePhone/{id}", produces ="application/json")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id,@RequestBody @Valid PhoneNumberUpdateDTO updateDTO) {
      employeeService.updateCellphoneNumber(id, updateDTO);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Desabilita um funcionário", description = "Desabilita um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @PatchMapping(value = "/disable/{id}", produces ="application/json")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
       employeeService.toggleEmployeeStatus(id);
        return ResponseEntity.noContent().build();
    }
}
