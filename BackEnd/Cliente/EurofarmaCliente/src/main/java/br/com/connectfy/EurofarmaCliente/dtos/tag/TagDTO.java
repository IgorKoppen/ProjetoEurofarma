package br.com.connectfy.EurofarmaCliente.dtos.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record TagDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, message = "O nome da Tag deve ter 3 dígitos")
        String name,
        @NotBlank(message = "A cor não pode ser vazio")
        @Size(min = 7, max = 7, message = "A cor deve ter 7 dígitos")
        String color
        ) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

