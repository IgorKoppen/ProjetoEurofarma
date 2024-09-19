package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import java.io.Serial;
import java.io.Serializable;

public class InstructorDetailsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private Long employeeRegistration;

    // Constructors

    public InstructorDetailsDTO() {}

    public InstructorDetailsDTO(String name, String surname, Long employeeRegistration) {
        this.name = name;
        this.surname = surname;
        this.employeeRegistration = employeeRegistration;
    }

    // Getters and Setters

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
}
