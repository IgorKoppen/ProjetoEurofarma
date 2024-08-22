package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import java.io.Serial;
import java.io.Serializable;

public record InstructorIdAndFullNameAndEmployeeRegistrarionDTO(Long id, String name, String surname, String fullName,Long employeeRegistrarion) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
