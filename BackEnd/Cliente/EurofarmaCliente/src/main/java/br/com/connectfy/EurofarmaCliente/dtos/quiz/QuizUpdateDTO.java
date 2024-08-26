package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record QuizUpdateDTO (
        @NotBlank(message = "É necessário um nome")
        String nome,
        @NotBlank(message = "É necessário uma descrição")
        String description,
        @NotBlank(message = "É necessário uma nota mínima")
        Integer notaMinima,
        @NotNull(message = "É necessário informar o número de questões")
        @Positive(message = "O valor deve ser positivo")
        Integer questionsNumber,
        List<QuestionDTO> questions
) {
}
