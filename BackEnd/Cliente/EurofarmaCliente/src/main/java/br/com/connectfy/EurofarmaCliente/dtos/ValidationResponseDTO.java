package br.com.connectfy.EurofarmaCliente.dtos;

public class ValidationResponseDTO {
    private Boolean passed;

    public ValidationResponseDTO(Boolean passed) {
        this.passed = passed;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
