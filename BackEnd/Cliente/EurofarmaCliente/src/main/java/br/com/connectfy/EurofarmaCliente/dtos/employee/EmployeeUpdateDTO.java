package br.com.connectfy.EurofarmaCliente.dtos.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record EmployeeUpdateDTO(
        @NotBlank(message = "O número de celular é obrigatório e não pode estar em branco.")
        String cellphoneNumber,
        @NotNull(message = "O cargo do funcionário é obrigatório e deve ser especificado.")
        Long roleId,
        @NotNull(message = "O funcionário deve ter pelo menos uma permissão atribuída.")
        Set<Long> permissionsIds
) {
}
