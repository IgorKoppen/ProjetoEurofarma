package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeTrainingKey implements Serializable {

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "training_id")
    Long trainingId;

    public EmployeeTrainingKey() {
    }

    public EmployeeTrainingKey(Long employeeId, Long trainingId) {
        this.employeeId = employeeId;
        this.trainingId = trainingId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeTrainingKey that)) return false;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(trainingId, that.trainingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, trainingId);
    }
}
