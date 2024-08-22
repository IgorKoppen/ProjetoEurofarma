package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.services.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/permission")
@Tag(name = "Permission", description = "Controller Permission")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @Operation(summary = "Consulta todas permições", description = "Retorna todas as permições",
            tags = {"Permission"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PermissionDTO>> findAll() {
        List<PermissionDTO> permissionDTOS = permissionService.findAll();
        return ResponseEntity.ok(permissionDTOS);
    }


    @Operation(summary = "Consulta uma permição", description = "Retorna uma permição a partir de um id",
            tags = {"Permission"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDTO> findById(@PathVariable Long id) {
        PermissionDTO permissionDTO = permissionService.findById(id);
        return ResponseEntity.ok(permissionDTO);
    }
}
