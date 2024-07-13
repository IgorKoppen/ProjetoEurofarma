package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record TrainingHistoricDTO(Long id,
                                  @NotBlank(message = "Nome não pode ser vazio!")String name,
                                  String code,
                                  @PastOrPresent(message = "Data de criação tem que ser atual ou passada") LocalDateTime creationDate,
                                  LocalDateTime closingDate,
                                  boolean status,
                                  @NotBlank(message = "Password não pode ser vazio!") String password,
                                  @NotBlank(message = "Descrição não pode ser vazia!") String description,
                                  List<String> instructorName,
                                  @NotBlank List<Tag> tags,
                                  @JsonIgnore List<Employee> employees) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
