package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class AlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AlreadyExistException(String ex) {
        super(ex);
    }
}
