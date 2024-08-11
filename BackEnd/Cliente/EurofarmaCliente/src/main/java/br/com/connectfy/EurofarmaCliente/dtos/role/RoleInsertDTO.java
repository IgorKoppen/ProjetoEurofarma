package br.com.connectfy.EurofarmaCliente.dtos.role;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

public record RoleInsertDTO(
        @JsonProperty("role_name")
        @NotBlank(message = "O campo não pode ser nulo")
        String roleName,
        @NotNull(message = "Deve ter um cargo")
        Long departmentId
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
