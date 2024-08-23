package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import java.io.Serial;
import java.io.Serializable;

public record InstructorIdAndFullNameAndEmployeeRegistrationDTO(Long id, String name, String surname, String fullName, Long employeeRegistration) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

