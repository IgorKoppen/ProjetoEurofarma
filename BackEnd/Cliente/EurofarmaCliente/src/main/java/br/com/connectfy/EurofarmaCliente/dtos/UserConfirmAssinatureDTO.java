package br.com.connectfy.EurofarmaCliente.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserConfirmAssinatureDTO(
        @NotNull(message = "User ID não pode ser vazio")
        Long userId,
        @NotBlank(message = "Assinatura não pode ser vazio")
        String code,
        @NotBlank(message = "Assinatura não pode ser vazio")
        String password,
        @NotBlank(message = "Assinatura não pode ser vazio")
        String assinatura) {
}
