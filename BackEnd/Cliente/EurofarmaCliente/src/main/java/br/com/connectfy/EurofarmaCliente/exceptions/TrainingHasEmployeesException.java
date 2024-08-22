package br.com.connectfy.EurofarmaCliente.exceptions;
import java.io.Serial;


public class TrainingHasEmployeesException extends  RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public TrainingHasEmployeesException(String ex) {
        super(ex);
    }
}
