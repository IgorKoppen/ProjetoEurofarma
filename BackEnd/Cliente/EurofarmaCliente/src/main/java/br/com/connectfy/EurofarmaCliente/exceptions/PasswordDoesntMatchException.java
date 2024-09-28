package br.com.connectfy.EurofarmaCliente.exceptions;
import java.io.Serial;
public class PasswordDoesntMatchException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordDoesntMatchException(String ex) {
        super(ex);
    }
}
