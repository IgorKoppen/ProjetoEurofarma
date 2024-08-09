package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorInfo;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrainingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;

    private final String code;

    @JsonProperty("creation_date")
    private String creationDate;

    @JsonProperty("closing_date")
    private String closingDate;

    private final boolean isOpened;

    @NotBlank(message = "Password não pode ser vazio!")
    private String password;

    @NotBlank(message = "Descrição não pode ser vazia!")
    private String description;

    private List<InstructorInfo>  instructorsInfo;
    private List<TagInfoDTO> tags;

    public TrainingDTO(Training entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.creationDate = formatter.format(entity.getCreationDate());
        this.closingDate =  formatter.format(entity.getClosingDate());
        this.isOpened = entity.getStatus();
        this.password = entity.getPassword();
        this.description = entity.getDescription();
        this.instructorsInfo = entity.getInstructors().stream().map(InstructorInfo::new).toList();
        this.tags = entity.getTags().stream().map(TagInfoDTO::new).toList();
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

    public String getCreationDate() {
        return creationDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public List<InstructorInfo> getInstructorsInfo() {
        return instructorsInfo;
    }

    public List<TagInfoDTO> getTags() {
        return tags;
    }

}
