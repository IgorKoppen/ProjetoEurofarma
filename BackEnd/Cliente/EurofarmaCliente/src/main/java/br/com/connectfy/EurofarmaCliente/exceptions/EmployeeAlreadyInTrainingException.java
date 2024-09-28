package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;


public class EmployeeAlreadyInTrainingException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EmployeeAlreadyInTrainingException(String ex) {
        super(ex);
    }
}
