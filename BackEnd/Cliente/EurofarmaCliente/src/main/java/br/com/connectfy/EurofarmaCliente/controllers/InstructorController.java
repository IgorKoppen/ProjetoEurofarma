package br.com.connectfy.EurofarmaCliente.controllers;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorIdAndFullNameAndEmployeeRegistrarionDTO;
import br.com.connectfy.EurofarmaCliente.services.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;


@RestController
@RequestMapping("/eurofarma/instructor")
@Tag(name = "Instructor", description = "Controller Instructor")
@SecurityRequirement(name = "bearerAuth")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;



    @Operation(summary = "Consulta instrutores", description = "Retorna todos os instrutores",
            tags = {"Instructor"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<InstructorDTO>> findAll(){
        List<InstructorDTO> list = instructorService.findAll();
        return ResponseEntity.ok(list);
    }


    @Operation(summary = "Consulta instrutores com paginação", description = "Retorna todos instrutores com paginação",
            tags = {"Instructor"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/pagination", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<InstructorDTO>>> findWithPagination(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
            Pageable pageable, PagedResourcesAssembler<InstructorDTO> assembler) {
        Page<InstructorDTO> instructorDTOPage = instructorService.findWithPagination(pageable);
        PagedModel<EntityModel<InstructorDTO>> pagedModel = assembler.toModel(instructorDTOPage);
        return ResponseEntity.ok(pagedModel);
    }


    @Operation(summary = "Consulta o nome completo dos instrutores e o RE", description = "Retorna todos os nomes completos dos instrutores junto com RE",
            tags = {"Instructor"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            })
    @GetMapping(value = "/findAllFullNameEmployeeAndRegistration", produces = "application/json")
    public ResponseEntity<List<InstructorIdAndFullNameAndEmployeeRegistrarionDTO>> findAllFullNameAndRegistration() {
        List<InstructorIdAndFullNameAndEmployeeRegistrarionDTO> dto  = instructorService.findAllIdAndFullNameAndRegistration();
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "Consulta um instrutor", description = "Retorna um instrutor a partir de um id",
            tags = {"Instructor"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstructorDTO> findById(@PathVariable Long id) {
        InstructorDTO dto  = instructorService.findById(id);
        return ResponseEntity.ok(dto);
    }



}
