package br.com.connectfy.EurofarmaCliente.dtos;

import java.io.Serial;
import java.io.Serializable;

public record InstructorNameAndIdDTO(Long id, String name, String surname,String fullName) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
