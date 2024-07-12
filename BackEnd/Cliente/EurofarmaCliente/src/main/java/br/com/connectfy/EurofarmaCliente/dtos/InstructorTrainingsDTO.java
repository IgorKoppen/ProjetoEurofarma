package br.com.connectfy.EurofarmaCliente.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record InstructorTrainingsDTO(List<TrainingHistoricDTO> trainings) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
