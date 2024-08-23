package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorInfo;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String name;

    private final String code;

    @JsonProperty("creation_date")
    private String creationDate;

    @JsonProperty("closing_date")
    private String closingDate;

    private final boolean isOpened;

    private final String password;

    @NotBlank(message = "Descrição não pode ser vazia!")
    private String description;

    private final List<InstructorInfo>  instructorsInfo;

    private final List<TagDTO> tags;

    private final List<DepartmentDTO> departments;

    public TrainingDTO(Training entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.creationDate = formatter.format(entity.getCreationDate());
        this.closingDate =  formatter.format(entity.getClosingDate());
        this.isOpened = LocalDateTime.now().isBefore(entity.getClosingDate());
        this.password = entity.getPassword();
        this.description = entity.getDescription();
        this.instructorsInfo = entity.getInstructors().stream().map(InstructorInfo::new).toList();
        this.tags = entity.getTags().stream().map(TagDTO::new).toList();
        this.departments = entity.getDepartments().stream().map(DepartmentDTO::new).toList();
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

    public List<TagDTO> getTags() {
        return tags;
    }

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }
}
