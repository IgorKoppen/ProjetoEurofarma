package br.com.connectfy.EurofarmaCliente.exceptions;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class InputExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private  Map<String, List<String>>
            errors;

    public InputExceptionResponse(Date timestamp, String message, String details, Map<String, List<String>> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public     Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
