package br.com.connectfy.EurofarmaCliente.exceptions;
import org.springframework.security.core.AuthenticationException;
import java.io.Serial;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
