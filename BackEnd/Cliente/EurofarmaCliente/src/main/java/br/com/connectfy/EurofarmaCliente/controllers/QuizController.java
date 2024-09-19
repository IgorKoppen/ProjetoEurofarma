package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.ValidationResponseDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizValidateDTO;
import br.com.connectfy.EurofarmaCliente.services.QuizService;
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
@RequestMapping("/eurofarma/quiz")
@Tag(name = "Quiz", description = "Controller Quiz")
@SecurityRequirement(name = "bearerAuth")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Consulta Quizzes", description = "Retorna todos os quizzes",
            tags = {"Quiz"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<QuizDTO>> findAll() {
        List<QuizDTO> list = quizService.findAll();
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Consulta um quiz", description = "Retorna um quiz a partir de um id",
            tags = {"Quiz"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizDTO> findById(@PathVariable Long id) {
        QuizDTO quizDTO = quizService.findById(id);
        return ResponseEntity.ok(quizDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Cria um quiz", description = "Cria um novo quiz",
            tags = {"Quiz"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(produces ="application/json")
    public ResponseEntity<QuizDTO> insert(@RequestBody @Valid QuizInsertDTO quizDTO) {
        QuizDTO quizDTOInserted = quizService.insert(quizDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(quizDTOInserted.getId()).toUri();
        return ResponseEntity.created(uri).body(quizDTOInserted);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Atualiza um quiz", description = "Atualiza um quiz a partir de um id",
            tags = {"Quiz"},
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
    public ResponseEntity<QuizDTO> update(@PathVariable Long id, @RequestBody @Valid QuizUpdateDTO quizDTO) {
        QuizDTO quizDTOUpdated = quizService.update(id, quizDTO);
        return ResponseEntity.ok(quizDTOUpdated);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador')")
    @Operation(summary = "Exclui um Quiz", description = "Exclui um quiz a partir de um id",
            tags = {"Quiz"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Valida as respostas do quiz", description = "Valida as respostas do quiz e retorna se o usuário passou ou não",
            tags = {"Quiz"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PostMapping(value = "/validate/{quizId}", produces ="application/json")
    public ResponseEntity<ValidationResponseDTO> validateQuiz(@PathVariable Long quizId, @RequestBody QuizValidateDTO quizValidateBatchDTO) {
        Boolean isPassed = quizService.validateQuizAnswers(quizId, quizValidateBatchDTO);
        return ResponseEntity.ok(new ValidationResponseDTO(isPassed));
    }


}
