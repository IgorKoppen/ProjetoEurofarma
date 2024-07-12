package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record DepartmentDTO(Long id,
                            @JsonProperty("departName") @Size(min = 3, message = "Um departamento deve ter no mínimo 3 dígitos")
                            @NotBlank String departName,
                            List<Employee> employees) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
