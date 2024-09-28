package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record QuizInsertDTO(
        @NotBlank(message = "É necessário um nome")
        String nome,
        @NotBlank(message = "É necessário uma descrição")
        String description,
        @NotNull(message = "É necessário uma nota mínima")
        Integer notaMinima,
        @NotNull(message = "É necessário informar o número de questões")
        @Positive(message = "O valor deve ser positivo")
        Integer questionsNumber
) {
}
