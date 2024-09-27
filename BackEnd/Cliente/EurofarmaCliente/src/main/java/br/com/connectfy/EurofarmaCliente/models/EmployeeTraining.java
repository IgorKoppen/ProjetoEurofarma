package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_employee_trainings")
public class EmployeeTraining {

    @EmbeddedId
    EmployeeTrainingKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;


    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    Training training;

    @Column(nullable = false)
    @Lob
    String signature;

    @Column(nullable = false)
    LocalDateTime registrationDate;

    Integer quizTries;

    Double nota;

    public EmployeeTraining() {
    }

    public EmployeeTraining(EmployeeTrainingKey id, Employee employee, Training training, String signature) {
        this.id = id;
        this.employee = employee;
        this.training = training;
        this.signature = signature;
        this.registrationDate = LocalDateTime.now();
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getQuizTries() {
        return quizTries;
    }

    public void setQuizTries(Integer quizTries) {
        this.quizTries = quizTries;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
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
