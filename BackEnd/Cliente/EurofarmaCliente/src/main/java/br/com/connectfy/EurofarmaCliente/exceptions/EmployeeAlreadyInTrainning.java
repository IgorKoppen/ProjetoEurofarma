package br.com.connectfy.EurofarmaCliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyInTrainning extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EmployeeAlreadyInTrainning(String ex) {
        super(ex);
    }
}
