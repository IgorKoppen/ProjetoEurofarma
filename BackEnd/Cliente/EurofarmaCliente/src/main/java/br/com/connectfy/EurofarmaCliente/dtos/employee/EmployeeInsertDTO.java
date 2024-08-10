package br.com.connectfy.EurofarmaCliente.dtos.employee;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record EmployeeInsertDTO(
        @NotBlank(message = "O campo não pode ser nulo")
        @Size(min = 4, message = "O nome deve ter pelo menos 4 letras")
        String name,
        @NotBlank(message = "O campo não pode ser nulo")
        @Size(min = 4, message = "O sobrenome deve ter pelo menos 4 letras")
        String surname,
        @NotBlank(message = "O campo não pode ser nulo")
        String cellphoneNumber,
        List<Long> rolesIds,
        Long departmentId,
        List<Long> permissionsIds
) {
}
