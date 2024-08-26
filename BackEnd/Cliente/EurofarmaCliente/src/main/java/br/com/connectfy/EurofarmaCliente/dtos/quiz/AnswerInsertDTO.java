package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerInsertDTO(
        @NotBlank(message = "É necessário uma resposta")
        String answer,
        @NotNull(message = "É necessário informar se a questão é correta")
        Boolean isCorrect
) {
}
