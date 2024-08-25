package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class IncorrectPasswordException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
