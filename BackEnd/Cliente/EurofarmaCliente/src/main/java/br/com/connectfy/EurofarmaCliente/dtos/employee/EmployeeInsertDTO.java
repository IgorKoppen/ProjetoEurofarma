package br.com.connectfy.EurofarmaCliente.dtos.employee;


import jakarta.validation.constraints.*;

import java.util.Set;

public record EmployeeInsertDTO(

        @NotNull(message = "O Registro de funcionário é obrigatório.")
        @Min(value = 1, message = "O Registro de funcionário deve ter pelo menos 1.")
        @Max(value = 99999, message = "O Registro de funcionário deve ter no máximo 5 dígitos.")
        @Positive(message = "Registro de funcionário precisa ser positivo.")
        Long employeeRegistration,

        @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
        @Size(min = 4, message = "O nome deve ter pelo menos 4 caracteres.")
        String name,
        @NotBlank(message = "O sobrenome é obrigatório e não pode estar em branco.")
        @Size(min = 4, message = "O sobrenome deve ter pelo menos 4 caracteres.")
        String surname,
        @NotBlank(message = "O Registro de funcionário é obrigatório e não pode estar em branco.")

        @NotBlank(message = "O número de celular é obrigatório e não pode estar em branco.")
        String cellphoneNumber,
        @NotNull(message = "O funcionário deve ter um cargo especificado.")
        Long roleId,
        Set<Long> permissionsIds
) {
}
