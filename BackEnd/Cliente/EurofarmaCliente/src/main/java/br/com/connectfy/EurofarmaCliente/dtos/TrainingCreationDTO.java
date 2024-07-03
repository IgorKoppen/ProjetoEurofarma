package br.com.connectfy.EurofarmaCliente.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;
import java.util.List;

public record TrainingCreationDTO(
        @NotBlank(message = "Nome não pode ser vazio!")String name,
         @NotBlank(message = "Data de fechamento não pode ser vazia!") @JsonProperty("closing_date") String closingDate,
        @NotBlank(message = "Descrição não pode ser vazia!") String description,
        List<Long> instructor,
        List<Long> tags
) {
}
