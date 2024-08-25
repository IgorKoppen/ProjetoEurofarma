package br.com.connectfy.EurofarmaCliente.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ValidationErrorDTO extends ExceptionResponseDTO {

    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(Date timestamp, String message, String details) {
        super(timestamp, message, details);
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(x -> x.getFieldName().equals(fieldName));
        errors.add(new FieldMessageDTO(fieldName, message));
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }
}
