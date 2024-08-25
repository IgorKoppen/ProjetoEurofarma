package br.com.connectfy.EurofarmaCliente.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public record RoomParticipantsDTO(String participantFullName, Long employeeRegistration, String registrationDate) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
