package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.services.TagService;
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
import java.util.List;
@RestController
@RequestMapping("/eurofarma/tag")
@Tag(name = "Tag", description = "Controller Tag")
@SecurityRequirement(name = "bearerAuth")
public class TagController {

    @Autowired
    private TagService tagsService;


    @Operation(summary = "Cria uma tag", description = "Cria uma novo tag",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(produces = "application/json")
    public ResponseEntity<TagDTO> insert(@RequestBody @Valid TagDTO tagDTO) {
        TagDTO tagInfoDTO = tagsService.insert(tagDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tagInfoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(tagInfoDTO);
    }


    @Operation(summary = "Consulta tags", description = "Consulta todas tags",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TagDTO>> findAll(){
        List<TagDTO> TagInfoDTOs = tagsService.findAll();
        return ResponseEntity.ok(TagInfoDTOs);
    }


    @Operation(summary = "Consulta tags com paginação", description = "Consulta todas tags com paginação",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/pagination", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<TagDTO>>> findWithPagination(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
            Pageable pageable, PagedResourcesAssembler<TagDTO> assembler) {
        Page<TagDTO> tagDTOPage = tagsService.findWithPagination(pageable);
        PagedModel<EntityModel<TagDTO>> pagedModel = assembler.toModel(tagDTOPage);
        return ResponseEntity.ok(pagedModel);
    }


    @Operation(summary = "Consulta uma tag", description = "Consulta uma tag a partir de um id",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
        TagDTO tagInfoDTO = tagsService.getById(id);
        return ResponseEntity.ok(tagInfoDTO);
    }


    @Operation(summary = "Atualiza uma tag", description = "Atualiza uma tag a partir de um id",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value ="/{id}", produces = "application/json")
    public  ResponseEntity<TagDTO> update(@PathVariable Long id, @RequestBody @Valid TagDTO tagDTO) {
        TagDTO tagInfoDTO = tagsService.update(id, tagDTO);
        return ResponseEntity.ok(tagInfoDTO);
    }


    @Operation(summary = "Exclui uma tag", description = "Exclui uma tag a partir de um id",
            tags = {"tag"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
