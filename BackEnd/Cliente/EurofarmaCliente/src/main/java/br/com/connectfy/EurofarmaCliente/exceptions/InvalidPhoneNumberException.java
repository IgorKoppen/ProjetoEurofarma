package br.com.connectfy.EurofarmaCliente.exceptions;
import java.io.Serial;

public class InvalidPhoneNumberException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPhoneNumberException(String ex) {
        super(ex);
    }
}
