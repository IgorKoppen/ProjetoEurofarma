package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Trainning;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record InstructorDTO(Long id,
                            @NotBlank Employee employee,
                             List<Trainning> trainnings) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
