package br.com.connectfy.EurofarmaCliente.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponseDTO(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
