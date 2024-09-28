package br.com.connectfy.EurofarmaCliente.dtos;

public class FieldMessageDTO {
    private String fieldName;
    private String message;

    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldMessageDTO() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
