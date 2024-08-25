package br.com.connectfy.EurofarmaCliente.dtos.training;


import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class TrainingInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    private String name;

    @NotBlank(message = "Data de fechamento não pode ser vazia!")
    @JsonProperty("closing_date")
    private String closingDate;

    @NotBlank(message = "A descrição é obrigatória e não pode estar em branco.")
    private String description;


    @NotEmpty(message = "É necessário incluir ao menos um instrutor para criar um treinamento.")
    private Set<InstructorDTO> instructor;

    @NotEmpty(message = "É necessário incluir ao menos uma tag para criar um treinamento.")
    private final Set<TagDTO> tags;

    @NotEmpty(message = "É necessário incluir ao menos um departamento para criar um treinamento.")
    private Set<DepartmentDTO> department;

    @NotNull(message = "É necessário definir se é para mandar messagem para os funcionários dos departamentos.")
    private Boolean isToSendMessage;

    public TrainingInsertDTO(String name, String closingDate, String description, Set<InstructorDTO> instructor, Set<TagDTO> tags, Set<DepartmentDTO> department, Boolean isToSendMessage) {
        this.name = name;
        this.closingDate = closingDate;
        this.description = description;
        this.instructor = instructor;
        this.tags = tags;
        this.department = department;
        this.isToSendMessage = isToSendMessage;
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


    public Set<InstructorDTO> getInstructor() {
        return instructor;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public Set<DepartmentDTO> getDepartment() {
        return department;
    }

    public Boolean getToSendMessage() {
        return isToSendMessage;
    }
}
