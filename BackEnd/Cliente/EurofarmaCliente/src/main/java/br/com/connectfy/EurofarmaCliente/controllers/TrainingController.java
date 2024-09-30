package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.training.*;
import br.com.connectfy.EurofarmaCliente.services.TrainingService;
import br.com.connectfy.EurofarmaCliente.specification.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/eurofarma/training")
@Tag(name = "Training", description = "Controller Training")
@SecurityRequirement(name = "bearerAuth")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;


    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Cria um treinamento", description = "Cria um novo treinamento",
            tags = {"Training"},
            responses = {

                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<TrainingDTO> insert(@RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.insert(trainingCreationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trainingDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(trainingDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta treinamentos com paginação. Um parâmetro opcional de máscara de formatação de data pode ser fornecido.", description = "Retorna treinamentos com paginação",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/pagination", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<TrainingDTO>>> findWithPagination(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
            Pageable pageable,@Parameter(hidden = true) PagedResourcesAssembler<TrainingDTO> assembler,
            @RequestParam(value = "formatter", required = false) String formatterStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                formatterStr != null ? formatterStr : "dd/MM/yyyy HH:mm:ss");
        Page<TrainingDTO> trainingDTOPage = trainingService.findWithPagination(pageable,formatter);
        PagedModel<EntityModel<TrainingDTO>> pagedModel = assembler.toModel(trainingDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta um treinamento. Um parâmetro opcional de máscara de formatação de data pode ser fornecido.", description = "Retorna treinamento com base no id.",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainingDTO> findById(@PathVariable Long id, @RequestParam(value = "formatter", required = false) String formatterStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                formatterStr != null ? formatterStr : "dd/MM/yyyy HH:mm:ss");
        TrainingDTO trainingDTO = trainingService.findById(id,formatter);
        return ResponseEntity.ok(trainingDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Atualiza um treinamento", description = "Atualiza um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TrainingDTO> update(@PathVariable Long id, @RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.update(id, trainingCreationDTO);
        return ResponseEntity.ok(trainingDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Cancela um treinamento", description = "Cancela um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        trainingService.cancelTraining(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Procura um treinamento", description = "Retorna um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/getRoomByCode/{employeeId}/{code}", produces = "application/json")
    public ResponseEntity<TrainingDTO> findByCode(@PathVariable Long employeeId, @PathVariable String code) {
        TrainingDTO trainingDTO = trainingService.findByCode(employeeId, code);
        return ResponseEntity.ok(trainingDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Procura os treinamentos de um funcionário", description = "Retorna todos treinamentos a partir de um id de um funcionário",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/EmployeeTrainings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingOfEmployeeDTO>> findEmployeeTrainingsByEmployeeId(@PathVariable Long id) {
        List<TrainingOfEmployeeDTO> dto = trainingService.findEmployeeTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Procura os treinamentos de um instrutor", description = "Retorna todos treinamentos a partir de um id de um instrutor",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/InstructorTrainings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrainingWithEmployeesInfo>> findInstructorTrainingsByInstructorId(@PathVariable Long id) {
        List<TrainingWithEmployeesInfo> dto = trainingService.findInstructorTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Procura um treinamento de instrutor por tag", description = "Retorna todos treinamentos a partir de um id de um instrutor e uma tag",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping("/findTrainingByInstructorAndTag/{id}")
    public ResponseEntity<List<TrainingWithEmployeesInfo>> findInstructorTrainingsByTag(@PathVariable Long id, @RequestParam String tag) {
        List<TrainingWithEmployeesInfo> dto = trainingService.findTrainingByIdAndTag(id, tag);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Verifica a senha do treinamento", description = "Verifica se a senha informada pelo funcionário" +
            "está correta, se está encerrada ou se o funcionário já está em um treinamento",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @GetMapping(value = "/confirmPassword", produces = "application/json")
    public ResponseEntity<Void> confirmPassword(@RequestParam Long userId,@RequestParam String code, @RequestParam String password) {
         trainingService.confirmPassword(userId,code, password);
         return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
   @Operation(summary = "Procura os funcionários de um treinamento", description = "Retorna todos os funcionários que realizaram um treinamento",
           tags = {"Training"},
           responses = {
                   @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
           })
   @GetMapping(value = "/getAllRoomParticipants/{roomId}", produces = "application/json")
   public ResponseEntity<List<RoomParticipantsDTO>> getAllRoomParticipants(@PathVariable Long roomId) {
        List<RoomParticipantsDTO> roomParticipantsDTOS = trainingService.findAllRoomParticipants(roomId);
       return ResponseEntity.ok(roomParticipantsDTOS);
   }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Adiciona um funcionário em um treinamento", description = "Insere um funcionário em um treinamento",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value = "/addEmployee", produces = "application/json")
    public ResponseEntity<Void> addEmployeeIntoTraining(@Valid @RequestBody UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        trainingService.addEmployeeInTraining(userConfirmAssinatureDTO);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Busca treinamentos com base em critérios específicos",
            description = "Realiza a busca de treinamentos utilizando diversos parâmetros opcionais," +
                    " como nome, data de fechamento, tags, departamento," +
                    " e registro de funcionários ou instrutores." +
                    " Retorna uma lista paginada de treinamentos que correspondem aos critérios fornecidos.",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<TrainingDTO>>> searchTrainings(
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "tagId", required = false) Long tagId,
                                          @RequestParam(value = "tagName", required = false) String tagName,
                                          @RequestParam(value = "departmentId", required = false) Long departmentId,
                                          @RequestParam(value = "departmentName", required = false) String departmentName,
                                          @RequestParam(value = "instructorRegistration", required = false) Long instructorRegistration,
                                          @RequestParam(value = "employeeRegistration", required = false) Long employeeRegistration,
                                             @PageableDefault(direction = Sort.Direction.ASC)
                                                 Pageable pageable,
                                          @Parameter(hidden = true)   PagedResourcesAssembler<TrainingDTO> assembler) {

        List<SearchCriteria> params = new ArrayList<>();


        if (name != null) {
            params.add(new SearchCriteria("name", ":", name));
        }
        if (tagId != null) {
            params.add(new SearchCriteria("tags.id", "=", tagId));
        }
        if (tagName != null) {
            params.add(new SearchCriteria("tagName", "=", tagName));
        }
        if (departmentId != null) {
            params.add(new SearchCriteria("departments.id", "=", departmentId));
        }
        if (departmentName != null) {
            params.add(new SearchCriteria("departmentName", "=", departmentName));
        }
        if (instructorRegistration != null) {
            params.add(new SearchCriteria("instructorRegistration", "=", instructorRegistration));
        }
        if (employeeRegistration != null) {
            params.add(new SearchCriteria("employeeRegistration", "=", employeeRegistration));
        }
        Page<TrainingDTO> trainingDTOPage =  trainingService.search(params, pageable);
        PagedModel<EntityModel<TrainingDTO>> pagedModel = assembler.toModel(trainingDTOPage);

        return ResponseEntity.ok(pagedModel);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Busca as informações dos treinamentos",
            description = "Realiza a busca de treinamentos e retorna as informações dos treinadores e funcionários que realizaram o treinamento",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainingDetailsDTO> getTrainingInfo(@PathVariable Long id) {
        TrainingDetailsDTO trainingDetails = trainingService.findTrainingInfoById(id);
        return ResponseEntity.ok(trainingDetails);
    }

}
