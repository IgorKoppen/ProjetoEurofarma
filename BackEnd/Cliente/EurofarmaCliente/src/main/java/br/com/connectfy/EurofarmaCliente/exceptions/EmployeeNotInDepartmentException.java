package br.com.connectfy.EurofarmaCliente.exceptions;

public class EmployeeNotInDepartmentException extends RuntimeException {
    public EmployeeNotInDepartmentException(String message) {
        super(message);
    }
}
