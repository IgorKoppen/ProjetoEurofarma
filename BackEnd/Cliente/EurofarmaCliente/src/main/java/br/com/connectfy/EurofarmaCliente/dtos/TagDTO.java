package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Trainning;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record TagDTO(
        Long id,
        @NotBlank @Size(min = 3, message = "O nome da Tag deve ter 3 dígitos") String name,
        @NotBlank @Size(min = 7, max = 7, message = "A cor deve ter 7 dígitos") String color,
        List<Trainning> trainings) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
