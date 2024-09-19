package br.com.connectfy.EurofarmaCliente.dtos;

import jakarta.persistence.Lob;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EmployeeTrainingInfoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String surname;
    private Long employeeRegistration;
    @Lob
    private String signature;
    private LocalDateTime registrationDate;

    public EmployeeTrainingInfoDTO() {
    }

    public EmployeeTrainingInfoDTO(Long id, String name, String surname, Long employeeRegistration, String signature, LocalDateTime registrationDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.employeeRegistration = employeeRegistration;
        this.signature = signature;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getEmployeeRegistration() {
        return employeeRegistration;
    }

    public void setEmployeeRegistration(Long employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
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
}