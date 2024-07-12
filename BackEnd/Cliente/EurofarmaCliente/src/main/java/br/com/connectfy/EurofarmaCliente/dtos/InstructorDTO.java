package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Training;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record InstructorDTO(Long id,
                            @NotBlank Employee employee,
                             List<Training> trainnings,
                            List<String> instructorName) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
