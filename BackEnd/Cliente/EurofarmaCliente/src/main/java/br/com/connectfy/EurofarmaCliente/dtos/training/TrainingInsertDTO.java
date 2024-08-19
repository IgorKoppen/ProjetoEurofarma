package br.com.connectfy.EurofarmaCliente.dtos.training;


import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class TrainingInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;

    @Future(message = "Data de fechamento tem que ser no futuro")
    @JsonProperty("closing_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime closingDate;

    @NotBlank(message = "Descrição não pode ser vazia!")
    private String description;

    private final Set<Long> departmentIdsToSendMessage;

    @NotEmpty(message = "Precisa de instrutores para gerar um Treinamento")
    private Set<InstructorDTO>  instructor;

    @NotEmpty(message = "Precisa ao menos uma tag para gerar um Treinamento")
    private final Set<TagDTO> tags;


    public TrainingInsertDTO(Long id, String name, LocalDateTime closingDate, String description, Set<Long> departmentIdsToSendMessage, Set<InstructorDTO> instructor, Set<TagDTO> tags) {
        this.id = id;
        this.name = name;
        this.closingDate = closingDate;
        this.description = description;
        this.departmentIdsToSendMessage = departmentIdsToSendMessage;
        this.instructor = instructor;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public String getDescription() {
        return description;
    }

    public Set<Long> getDepartmentIdsToSendMessage() {
        return departmentIdsToSendMessage;
    }

    public Set<InstructorDTO> getInstructor() {
        return instructor;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }
}
