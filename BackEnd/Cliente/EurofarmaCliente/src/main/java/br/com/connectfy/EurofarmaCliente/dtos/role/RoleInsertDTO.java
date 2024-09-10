package br.com.connectfy.EurofarmaCliente.dtos.role;

import jakarta.validation.constraints.NotBlank;

public record RoleInsertDTO(
        @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
        String nome
) {
}
