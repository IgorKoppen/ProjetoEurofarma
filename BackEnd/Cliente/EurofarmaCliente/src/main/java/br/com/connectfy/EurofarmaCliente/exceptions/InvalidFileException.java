package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class InvalidFileException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFileException(String message) {
        super(message);
    }
}
