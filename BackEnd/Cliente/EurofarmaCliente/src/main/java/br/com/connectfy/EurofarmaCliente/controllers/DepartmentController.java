package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/department")
@Tag(name = "Department", description = "Controller Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(produces ="application/json")
    @Operation(summary = "Cria um departamento", description = "Cria um novo departamento",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    public ResponseEntity<DepartmentDTO> insert(@Validated @RequestBody DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departmentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(departmentDTO);
    }

    @GetMapping(produces ="application/json")
    @Operation(summary = "Lista departamentos", description = "Retorna todos os departamentos",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        List<DepartmentDTO> departmentDTOS = departmentService.findAll();
        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping(value = "/{id}", produces ="application/json")
    @Operation(summary = "Consulta um departamento", description = "Retorna um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.findById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @PutMapping(value = "/{id}", produces ="application/json")
    @Operation(summary = "Atualiza um departamento", description = "Atualiza um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id,
                                                @Validated
                                                @RequestBody DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.update(id, dto);
        return ResponseEntity.ok(departmentDTO);
    }

    @DeleteMapping(value = "/{id}", produces ="application/json")
    @Operation(summary = "Exclui um departamento", description = "Exclui um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
