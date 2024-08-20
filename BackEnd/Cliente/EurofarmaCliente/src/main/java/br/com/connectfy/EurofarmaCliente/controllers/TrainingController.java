package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingWithEmployeesInfo;
import br.com.connectfy.EurofarmaCliente.services.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/training")
@Tag(name = "Training", description = "Controller Training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping(produces = "aplication/json")
    @Operation(summary = "Cria um treinamento", description = "Cria um novo treinamento",
            tags = {"Training"},
            responses = {

                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
        public ResponseEntity<TrainingDTO> insert(@RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.insert(trainingCreationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trainingDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(trainingDTO);
    }

    @GetMapping(value = "/pagination", produces = "aplication/json")
    @Operation(summary = "Consulta treinamentos com paginação", description = "Retorna treinamentos com paginação",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    public ResponseEntity<PagedModel<EntityModel<TrainingDTO>>> findWithPagination(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
            Pageable pageable, PagedResourcesAssembler<TrainingDTO> assembler) {
        Page<TrainingDTO> trainingDTOPage = trainingService.findWithPagination(pageable);
        PagedModel<EntityModel<TrainingDTO>> pagedModel = assembler.toModel(trainingDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping(value = "/{id}", produces = "aplication/json")
    @Operation(summary = "Consulta treinamentos com paginação", description = "Retorna treinamentos com paginação",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<TrainingDTO> findById(@PathVariable Long id) {
        TrainingDTO trainingDTO = trainingService.findById(id);
        return ResponseEntity.ok(trainingDTO);
    }

    @PutMapping(value = "/{id}", produces = "aplication/json")
    @Operation(summary = "Atualiza um treinamento", description = "Atualiza um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<TrainingDTO> update(@PathVariable Long id, @RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.update(id, trainingCreationDTO);
        return ResponseEntity.ok(trainingDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    @Operation(summary = "Cancela um treinamento", description = "Cancela um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        trainingService.cancelTraining(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/getRoomByCode/{code}", produces = "aplication/json")
    @Operation(summary = "Procura um treinamento", description = "Retorna um treinamento a partir de um id",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<TrainingDTO> findByCode(@PathVariable  String code) {
        TrainingDTO trainingDTO = trainingService.findByCode(code);
        return ResponseEntity.ok(trainingDTO);
    }
    @GetMapping(value = "/EmployeeTrainings/{id}", produces = "aplication/json")
    @Operation(summary = "Procura os treinamentos de um funcionário", description = "Retorna todos treinamentos a partir de um id de um funcionário",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<List<TrainingOfEmployeeDTO>> findEmployeeTrainingsByEmployeeId(@PathVariable Long id) {
        List<TrainingOfEmployeeDTO> dto = trainingService.findEmployeeTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/InstructorTrainings/{id}", produces = "aplication/json")
    @Operation(summary = "Procura os treinamentos de um instrutor", description = "Retorna todos treinamentos a partir de um id de um instrutor",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<List<TrainingWithEmployeesInfo>> findInstructorTrainingsByInstructorId(@PathVariable Long id) {
        List<TrainingWithEmployeesInfo> dto = trainingService.findInstructorTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/confirmPassword", produces = "aplication/json")
    @Operation(summary = "Verifica a senha do treinamento", description = "Verifica se a senha informada pelo funcionário" +
            "está correta, se está encerrada ou se o funcionário já está em um treinamento",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<Void> confirmPassword(@RequestParam Long userId,@RequestParam String code, @RequestParam String password) {
         trainingService.confirmPassword(userId,code, password);
         return ResponseEntity.noContent().build();
    }
   @GetMapping(value = "/getAllRoomParticipants/{roomId}", produces = "aplication/json")
   @Operation(summary = "Procura os funcionários de um treinamento", description = "Retorna todos os funcionários que realizaram um treinamento",
           tags = {"Training"},
           responses = {
                   @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
           })
   public ResponseEntity<List<RoomParticipantsDTO>> getAllRoomParticipants(@PathVariable Long roomId) {
        List<RoomParticipantsDTO> roomParticipantsDTOS = trainingService.findAllRoomParticipants(roomId);
       return ResponseEntity.ok(roomParticipantsDTOS);
   }

    @PutMapping(value = "/addEmployee", produces = "aplication/json")
    @Operation(summary = "Adiciona um funcionário em um treinamento", description = "Insere um funcionário em um treinamento",
            tags = {"Training"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> addEmployeeIntoTraining(@RequestBody UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        trainingService.addEmployeeInTraining(userConfirmAssinatureDTO);
        return ResponseEntity.noContent().build();
    }
}
