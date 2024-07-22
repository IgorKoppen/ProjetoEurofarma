package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class AlreadyExisteException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AlreadyExisteException(String ex) {
        super(ex);
    }
}
