package br.com.connectfy.EurofarmaCliente.dtos.role;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public record RoleDTO(
        @JsonProperty("role_name")
        @NotBlank(message = "O campo não pode ser nulo")
        String roleName) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;




}
