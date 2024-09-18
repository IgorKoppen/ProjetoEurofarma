package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerUpdateDTO;
import br.com.connectfy.EurofarmaCliente.services.AnswerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/answer")
@Tag(name = "Answer", description = "Controller Answer")
@SecurityRequirement(name = "bearerAuth")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Cria todas as respostas", description = "Cria todas as resposta enviadas",
            tags = {"Answer"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    @PostMapping(produces ="application/json")
    public ResponseEntity<List<AnswerDTO>> insertAll(@RequestBody @Valid List<AnswerInsertDTO> answerInsertDTOs) {
        List<AnswerDTO> answerDTOs = answerService.insertAll(answerInsertDTOs);
        return ResponseEntity.ok(answerDTOs);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Atualiza todas as respostas", description = "Atualiza as respostas de uma questão",
            tags = {"Answer"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(produces = "application/json")
    public ResponseEntity<List<AnswerDTO>> updateAll(@RequestParam(value = "existingIds", required = false) String existingIdsJson, @RequestBody @Valid List<AnswerUpdateDTO> answerUpdateDTOs) {
        List<Long> existingIds = new ArrayList<>();
        if (existingIdsJson != null && !existingIdsJson.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                existingIds = objectMapper.readValue(existingIdsJson, new TypeReference<List<Long>>() {});
            } catch (JsonProcessingException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        List<AnswerDTO> updatedAnswers = answerService.updateAll(answerUpdateDTOs, existingIds);

        return ResponseEntity.ok(updatedAnswers);
    }



    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Exclui uma resposta", description = "Exclui uma resposta com base em um id",
            tags = {"Answer"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
