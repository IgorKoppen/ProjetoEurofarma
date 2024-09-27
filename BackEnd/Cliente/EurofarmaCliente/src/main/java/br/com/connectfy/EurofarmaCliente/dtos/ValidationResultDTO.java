package br.com.connectfy.EurofarmaCliente.dtos;

public class ValidationResultDTO {
    private final Boolean passed;
    private final Double score;

    public ValidationResultDTO(Boolean passed, Double score) {
        this.passed = passed;
        this.score = score;
    }

    public Boolean getPassed() {
        return passed;
    }

    public Double getScore() {
        return score;
    }

}
