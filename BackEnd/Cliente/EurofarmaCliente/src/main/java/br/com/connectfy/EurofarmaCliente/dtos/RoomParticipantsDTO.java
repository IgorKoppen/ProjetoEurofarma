package br.com.connectfy.EurofarmaCliente.dtos;

import java.io.Serial;
import java.io.Serializable;

public record RoomParticipantsDTO(String participantFullName, Long employeeRegistration) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
