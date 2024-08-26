package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionInsertDTO(
        @NotBlank(message = "É necessário uma pergunta")
        String question,
        @NotNull(message = "Necessário informar um quiz")
        QuizInsertDTO quizDTO
) {
}
