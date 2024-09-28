package br.com.connectfy.EurofarmaCliente.dtos;

import java.util.List;

public class EmployeeRegistrationResponseDTO {
    private final int numberOfEmployeesInserted;
    private final List<EmployeeProcessingDTO> errors;

    public EmployeeRegistrationResponseDTO(int numberOfEmployeesInserted, List<EmployeeProcessingDTO> errors) {
        this.numberOfEmployeesInserted = numberOfEmployeesInserted;
        this.errors = errors;
    }

    public int getNumberOfEmployeesInserted() {
        return numberOfEmployeesInserted;
    }

    public List<EmployeeProcessingDTO> getErrors() {
        return errors;
    }
}