package br.com.connectfy.EurofarmaCliente.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ChangePasswordDTO(
        @NotEmpty(message = "Deve ter a senha antiga!")
        String oldPassword,
        @NotEmpty(message = "Deve ter a senha nova!")
        @Size(min = 8,message = "O mínimo para senha é 8 dígitos")
        String newPassword
) {

}
