package br.com.connectfy.EurofarmaCliente.dtos.training;


import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class TrainingInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;

    private String code;

    @PastOrPresent(message = "Data de criação tem que ser atual ou passada")
    @JsonProperty("creation_date")
    private LocalDateTime creationDate;

    @Future(message = "Data de fechamento tem que ser no futuro")
    @JsonProperty("closing_date")
    private LocalDateTime closingDate;

    private boolean status;

    @NotBlank(message = "Password não pode ser vazio!")
    private String password;

    @NotBlank(message = "Descrição não pode ser vazia!")
    private String description;

    private List<InstructorDTO>  instructor;
    private List<TagInfoDTO> tags;
    private Set<EmployeeTraining> employees;

    public TrainingInsertDTO(Training training) {
        this.id = training.getId();
        this.name = training.getName();
        this.code = training.getCode();
        this.creationDate = training.getCreationDate();
        this.closingDate = training.getClosingDate();
        this.status = training.getStatus();
        this.password = training.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public List<InstructorDTO> getInstructor() {
        return instructor;
    }

    public List<TagInfoDTO> getTags() {
        return tags;
    }

    public Set<EmployeeTraining> getEmployees() {
        return employees;
    }
}
