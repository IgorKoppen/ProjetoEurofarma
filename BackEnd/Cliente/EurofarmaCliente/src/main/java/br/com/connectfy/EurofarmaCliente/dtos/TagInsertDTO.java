package br.com.connectfy.EurofarmaCliente.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record TagInsertDTO(Long id,
                           @NotBlank @Size(min = 3, message = "O nome da Tag deve ter 3 dígitos") String name,
                           @NotBlank @Size(min = 7, max = 7, message = "A cor deve ter 7 dígitos") String color) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
