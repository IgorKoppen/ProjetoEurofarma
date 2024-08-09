package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTrainingKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;

public class EmployeeTrainingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private final EmployeeTrainingKey id;
    @JsonIgnore
    private final EmployeeDTO employee;
    private final TrainingDTO training;
    @JsonIgnore
    private final String signature;

    public EmployeeTrainingDTO(EmployeeTraining entity) {
        this.id = entity.getId();
        this.employee = null;
        this.training = new TrainingDTO(entity.getTraining());
        this.signature = entity.getSignature();
    }

    public EmployeeTrainingKey getId() {
        return id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public TrainingDTO getTraining() {
        return training;
    }

    public String getSignature() {
        return signature;
    }
}
