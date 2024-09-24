package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class InvalidDataException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDataException(String message) {
        super(message);
    }
}
