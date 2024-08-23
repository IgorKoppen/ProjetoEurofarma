package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorInfo;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainingWithEmployeesInfo implements Serializable {
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

    private final String description;

    private Set<InstructorInfo> instructorsInfo = new HashSet<>();

    private Set<TagDTO> tags = new HashSet<>();

    private Set<DepartmentDTO> departments = new HashSet<>();

    public TrainingWithEmployeesInfo(Training entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.creationDate = formatter.format(entity.getCreationDate());
        this.closingDate = formatter.format(entity.getClosingDate());
        this.isOpened = LocalDateTime.now().isBefore(entity.getClosingDate());
        this.password = entity.getPassword();
        this.description = entity.getDescription();
        if(entity.getInstructors() != null) {
            this.instructorsInfo = entity.getInstructors().stream().map(InstructorInfo::new).collect(Collectors.toSet());
        }
        if(entity.getTags() != null){
            this.tags = entity.getTags().stream().map(TagDTO::new).collect(Collectors.toSet());
        }
        if(entity.getDepartments() != null){
            this.departments = entity.getDepartments().stream().map(DepartmentDTO::new).collect(Collectors.toSet());
        }
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

    public Set<InstructorInfo> getInstructorsInfo() {
        return instructorsInfo;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public Set<DepartmentDTO> getDepartments() {
        return departments;
    }
}