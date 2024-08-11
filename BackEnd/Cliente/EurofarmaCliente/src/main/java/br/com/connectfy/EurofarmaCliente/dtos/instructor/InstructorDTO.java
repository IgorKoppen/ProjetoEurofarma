package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class InstructorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private  Long id;
    @NotBlank(message = "Campo nome empregado não pode ser vazio")
    private final EmployeeInfoDTO employee;
    private final List<TrainingDTO> trainnings;

    public InstructorDTO(Instructor entity) {
        this.id = entity.getId();
        this.employee = new EmployeeInfoDTO(entity.getEmployee());
        this.trainnings = entity.getTrainings().stream().map(TrainingDTO::new).toList();
    }

    public InstructorDTO(EmployeeInfoDTO employee, List<TrainingDTO> trainings) {
        this.employee = employee;
        this.trainnings = trainings;
    }

    public Long getId() {
        return id;
    }

    public EmployeeInfoDTO getEmployee() {
        return employee;
    }

    public List<TrainingDTO> getTrainnings() {
        return trainnings;
    }

}
