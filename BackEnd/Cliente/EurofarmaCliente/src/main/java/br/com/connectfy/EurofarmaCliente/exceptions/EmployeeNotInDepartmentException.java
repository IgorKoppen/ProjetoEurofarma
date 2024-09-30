package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class EmployeeNotInDepartmentException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public EmployeeNotInDepartmentException(String message) {
        super(message);
    }
}
