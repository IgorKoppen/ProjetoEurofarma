package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record TrainingDTO(Long id,
                          @NotBlank(message = "Nome não pode ser vazio!")String name,
                          String code,
                          @PastOrPresent(message = "Data de criação tem que ser atual ou passada") @JsonProperty("creation_date") LocalDateTime creationDate,
                          @Future(message = "Data de fechamento tem que ser no futuro") @JsonProperty("closing_date") LocalDateTime closingDate,
                          boolean status,
                          @NotBlank(message = "Password não pode ser vazio!") String password,
                          @NotBlank(message = "Descrição não pode ser vazia!") String description,
                          List<Instructor>  instructor,
                          List<Tag> tags,
                          List<Employee> employees) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
