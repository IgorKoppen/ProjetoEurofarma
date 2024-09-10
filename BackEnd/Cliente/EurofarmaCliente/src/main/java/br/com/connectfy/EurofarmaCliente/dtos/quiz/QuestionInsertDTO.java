package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionInsertDTO(
        @NotBlank(message = "É necessário uma pergunta")
        String question,
        @NotNull(message = "Necessário informar um ID de quiz")
        Long quizId,
        @NotNull(message = "Necessário informar os IDs das respostas")
        List<Long> answerIds
) {
}
