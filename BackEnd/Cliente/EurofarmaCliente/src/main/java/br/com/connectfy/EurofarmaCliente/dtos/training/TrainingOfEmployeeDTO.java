package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.models.EmployeeTraining;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingOfEmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String name;

    @JsonProperty("creation_date")
    private String creationDate;

    @JsonProperty("closing_date")
    private String closingDate;

    private final boolean isOpened;

    private final String description;

    private final List<InstructorInfoDTO> instructorsInfo;

    private final List<TagDTO> tags;

    private final String registrationDate;

    private final String signature;

    public TrainingOfEmployeeDTO(EmployeeTraining employeeTraining) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss,SSS");

        Training entity = employeeTraining.getTraining();

        this.id = entity.getId();
        this.name = entity.getName();
        this.creationDate = formatter.format(entity.getCreationDate());
        this.closingDate = formatter.format(entity.getClosingDate());
        this.isOpened = LocalDateTime.now().isBefore(entity.getClosingDate());
        this.description = entity.getDescription();
        this.instructorsInfo = entity.getInstructors().stream().map(InstructorInfoDTO::new).toList();
        this.tags = entity.getTags().stream().map(TagDTO::new).toList();
        this.registrationDate = formatter.format(employeeTraining.getRegistrationDate());
        this.signature = employeeTraining.getSignature();
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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


    public String getDescription() {
        return description;
    }

    public List<InstructorInfoDTO> getInstructorsInfo() {
        return instructorsInfo;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getSignature() {
        return signature;
    }


}