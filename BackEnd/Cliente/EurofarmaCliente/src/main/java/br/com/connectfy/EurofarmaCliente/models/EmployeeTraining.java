package br.com.connectfy.EurofarmaCliente.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Clob;

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
}
