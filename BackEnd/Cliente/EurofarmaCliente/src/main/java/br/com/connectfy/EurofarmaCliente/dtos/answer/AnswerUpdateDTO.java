package br.com.connectfy.EurofarmaCliente.dtos.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerUpdateDTO(
        @NotBlank(message = "É necessário uma resposta")
        String answer,
        @NotNull(message = "É necessário informar se a questão é correta")
        Boolean isCorrect
) {
}
