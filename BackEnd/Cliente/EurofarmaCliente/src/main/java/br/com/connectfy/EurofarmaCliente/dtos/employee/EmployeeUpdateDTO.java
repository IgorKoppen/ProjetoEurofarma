package br.com.connectfy.EurofarmaCliente.dtos.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record EmployeeUpdateDTO(
        @NotBlank(message = "O campo não pode ser nulo")
        String cellphoneNumber,
        @NotNull(message = "O funcionário deve ter um cargo")
        Long roleId,
        @NotNull(message = "O funcionário deve ter uma permissão")
        Set<Long> permissionsIds
) {
}
