package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuizValidateDTO(
        @NotNull(message = "Sem id da questao")
        List<Long> questionIds,
        @NotBlank(message = "Resposta nao enviada")
        List<String> userAnswers
) {
}
