package br.com.connectfy.EurofarmaCliente.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserConfirmAssinatureDTO(
        @NotNull(message = "O ID do usuário é obrigatório e não pode ser nulo.")
        Long userId,
        @NotBlank(message = "O código de confirmação é obrigatório e não pode estar em branco.")
        String code,
        @NotBlank(message = "A senha é obrigatória e não pode estar em branco.")
        String password,
        @NotBlank(message = "A assinatura é obrigatória e não pode estar em branco.")
        String signature,
        Integer quizTries
) {
}
