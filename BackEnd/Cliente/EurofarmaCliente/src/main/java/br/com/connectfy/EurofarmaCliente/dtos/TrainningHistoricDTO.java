package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Employee;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record TrainningHistoricDTO(Long id,
                                   @NotBlank(message = "Nome não pode ser vazio!")String name,
                                   String code,
                                   @PastOrPresent(message = "Data de criação tem que ser atual ou passada") LocalDateTime creationDate,
                                   LocalDateTime closingDate,
                                   boolean status,
                                   @NotBlank(message = "Password não pode ser vazio!") String password,
                                   @NotBlank(message = "Descrição não pode ser vazia!") String description,
                                   @JsonManagedReference List<Instructor> instructor,
                                   @JsonManagedReference @NotBlank List<Tag> tags,
                                   @JsonBackReference List<Employee> employees) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
