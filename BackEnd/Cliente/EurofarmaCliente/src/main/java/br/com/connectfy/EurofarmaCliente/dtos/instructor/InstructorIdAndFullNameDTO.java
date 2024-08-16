package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import java.io.Serial;
import java.io.Serializable;

public record InstructorIdAndFullNameDTO(Long id, String fullName) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
