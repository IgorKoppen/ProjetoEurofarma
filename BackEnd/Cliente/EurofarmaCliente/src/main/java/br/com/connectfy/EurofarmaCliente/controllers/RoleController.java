package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInsertDTO;
import br.com.connectfy.EurofarmaCliente.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/role")
@Tag(name = "Role", description = "Controller Role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Cria um cargo", description = "Cria um novo cargo",
            tags = {"Role"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(produces = "application/json")
    public ResponseEntity<RoleDTO> insert(@RequestBody @Valid RoleInsertDTO dto) {
        RoleDTO roleDTO = roleService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDTO);
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta todos os cargos", description = "Retorna todos os cargos",
            tags = {"Role"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RoleDTO>> findAll(){
        List<RoleDTO> rolesDTO = roleService.findAll();
        return ResponseEntity.ok(rolesDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta um cargo", description = "Retorna um cargo pelo id",
            tags = {"Role"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        RoleDTO rolesDTO = roleService.findById(id);
        return ResponseEntity.ok(rolesDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Atualiza um cargo", description = "Atualiza um cargo a partir de um id",
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
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleInsertDTO dto) {
        RoleDTO roleDTO = roleService.update(id, dto);
        return ResponseEntity.ok(roleDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Exclui um cargos", description = "Exclui um cargos a partir de um id",
            tags = {"Role"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
