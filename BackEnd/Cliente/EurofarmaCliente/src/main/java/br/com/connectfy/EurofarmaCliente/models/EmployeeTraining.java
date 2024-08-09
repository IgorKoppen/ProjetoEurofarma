package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeTrainingDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class EmployeeTraining {

    @EmbeddedId
    EmployeeTrainingKey id;

    @JsonManagedReference
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;


    @JsonBackReference
    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    Training training;

    @Column(nullable = false)
    @Lob
    String signature;

    public EmployeeTraining() {
    }

    public EmployeeTraining(EmployeeTrainingKey id, Employee employee, Training training, String signature) {
        this.id = id;
        this.employee = employee;
        this.training = training;
        this.signature = signature;
    }

    public EmployeeTraining(EmployeeTrainingDTO dto) {
        this.id = dto.getId();
        this.employee = new Employee(dto.getEmployee());
        this.training = new Training(dto.getTraining());
        this.signature = dto.getSignature();
    }

    public EmployeeTraining(EmployeeTraining employeeTraining) {
    }

    public EmployeeTrainingKey getId() {
        return id;
    }

    public void setId(EmployeeTrainingKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeTraining that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(training, that.training) && Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
