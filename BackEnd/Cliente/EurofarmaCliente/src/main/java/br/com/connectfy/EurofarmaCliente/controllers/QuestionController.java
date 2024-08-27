package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionInsertDTO;
import br.com.connectfy.EurofarmaCliente.services.QuestionService;
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

import java.util.List;

@RestController
@RequestMapping("/eurofarma/question")
@Tag(name = "Question", description = "Controller Question")
@SecurityRequirement(name = "bearerAuth")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Cria uma questão", description = "Cria uma nova questão",
            tags = {"Question"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    @PostMapping(produces ="application/json")
    public ResponseEntity<QuestionDTO> insert(@RequestBody @Valid QuestionInsertDTO questionInsertDTO, @RequestBody @Valid List<Long> answerIds) {
        QuestionDTO questionDTO = questionService.insert(questionInsertDTO, answerIds);
        return ResponseEntity.ok(questionDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Consulta uma questão", description = "Retorna um questão a partir de um id",
            tags = {"Question"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDTO> findById(@PathVariable Long id) {
        QuestionDTO questionDTO = questionService.findById(id);
        return ResponseEntity.ok(questionDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Atualiza uma questão", description = "Atualiza uma questão a partir de um id",
            tags = {"Question"},
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
    public ResponseEntity<QuestionDTO> update(@PathVariable Long id, @RequestBody @Valid QuestionInsertDTO questionInsertDTO, List<Long> answerIds) {
        QuestionDTO questionDTO = questionService.update(id, questionInsertDTO, answerIds);
        return ResponseEntity.ok(questionDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Exclui uma questão", description = "Exclui uma questão a partir de um id",
            tags = {"Question"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
