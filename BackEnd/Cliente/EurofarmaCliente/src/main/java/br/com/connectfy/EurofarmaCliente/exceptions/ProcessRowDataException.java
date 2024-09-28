package br.com.connectfy.EurofarmaCliente.exceptions;

import java.io.Serial;

public class ProcessRowDataException extends Exception  {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProcessRowDataException(String ex) {
        super(ex);
    }
}
