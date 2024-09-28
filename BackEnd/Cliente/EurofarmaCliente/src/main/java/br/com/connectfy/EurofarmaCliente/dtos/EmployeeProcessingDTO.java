package br.com.connectfy.EurofarmaCliente.dtos;


public class EmployeeProcessingDTO  {
    private final String message;
    private final int rowIndex;

    public EmployeeProcessingDTO(String message, int rowIndex) {
        this.message = message;
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public String getMessage() {
        return message;
    }
}