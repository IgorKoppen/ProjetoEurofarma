package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public record RoleDTO(Long id, @JsonProperty("role_name") @NotBlank(message = "O campo não pode ser nulo") String roleName,
                      @NotBlank(message = "O campo não pode ser nulo") @JsonBackReference Employee employee) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
