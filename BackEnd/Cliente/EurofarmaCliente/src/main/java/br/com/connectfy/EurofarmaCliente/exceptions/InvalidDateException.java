package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class InvalidDateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String ex) {
        super(ex);
    }
}
