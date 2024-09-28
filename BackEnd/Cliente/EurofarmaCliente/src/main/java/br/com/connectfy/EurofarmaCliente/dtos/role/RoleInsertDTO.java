package br.com.connectfy.EurofarmaCliente.dtos.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleInsertDTO(
        @NotNull(message = "Departamento id é obrigatório") Long departmentId,
        @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
        String name
) {
}
