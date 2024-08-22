package br.com.connectfy.EurofarmaCliente.dtos.employee;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record EmployeeInsertDTO(
        @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
        @Size(min = 4, message = "O nome deve ter pelo menos 4 caracteres.")
        String name,
        @NotBlank(message = "O sobrenome é obrigatório e não pode estar em branco.")
        @Size(min = 4, message = "O sobrenome deve ter pelo menos 4 caracteres.")
        String surname,
        @NotBlank(message = "O número de celular é obrigatório e não pode estar em branco.")
        String cellphoneNumber,
        @NotNull(message = "O funcionário deve ter um cargo especificado.")
        Long roleId,
        Set<Long> permissionsIds
) {
}
