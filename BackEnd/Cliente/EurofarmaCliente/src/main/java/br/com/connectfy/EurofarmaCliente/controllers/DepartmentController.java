package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/department")
@Tag(name = "Department", description = "Controller de Departamento")
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Cria um departamento", description = "Cria um novo departamento",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(produces = "application/json")
    public ResponseEntity<DepartmentDTO> insert(@Validated @RequestBody DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departmentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(departmentDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Lista departamentos", description = "Retorna todos os departamentos",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DepartmentInfoDTO>> findAll() {
        List<DepartmentInfoDTO> departmentDTOS = departmentService.findAll();
        return ResponseEntity.ok(departmentDTOS);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Consulta um departamento", description = "Retorna um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentInfoDTO> findById(@PathVariable Long id) {
        DepartmentInfoDTO departmentDTO = departmentService.findById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Atualiza um departamento", description = "Atualiza um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id,
                                                @Validated
                                                @RequestBody DepartmentDTO dto) {
        DepartmentDTO departmentDTO = departmentService.update(id, dto);
        return ResponseEntity.ok(departmentDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Exclui um departamento", description = "Exclui um departamento a partir de um id",
            tags = {"Department"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
