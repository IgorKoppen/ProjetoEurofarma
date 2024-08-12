package br.com.connectfy.EurofarmaCliente.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PhoneNumberUpdateDTO(
        @NotEmpty(message = "Precisa de um número de telefone!")
        @Size(message = "A telefone deve ter no mínimo 10 dígitos", min = 10)
        String phoneNumber,
        @NotEmpty(message = "Senha do usuário é necessario para alteração do número de telefone!")
        String password) {
}
