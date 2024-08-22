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

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    private String name;

    @NotBlank(message = "Data de fechamento não pode ser vazia!")
    @JsonProperty("closing_date")
    private String closingDate;

    @NotBlank(message = "A descrição é obrigatória e não pode estar em branco.")
    private String description;

    private final Set<Long> departmentIdsToSendMessage;

    @NotEmpty(message = "É necessário incluir ao menos um instrutor para criar um treinamento.")
    private Set<InstructorDTO> instructor;

    @NotEmpty(message = "É necessário incluir ao menos uma tag para criar um treinamento.")
    private final Set<TagDTO> tags;

    public TrainingInsertDTO(Long id, String name, String closingDate, String description, Set<Long> departmentIdsToSendMessage, Set<InstructorDTO> instructor, Set<TagDTO> tags) {
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

    public String getClosingDate() {
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
