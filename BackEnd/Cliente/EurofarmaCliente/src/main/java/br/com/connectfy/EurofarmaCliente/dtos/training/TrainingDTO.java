package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingDTO {
    private Long id;
    private String name;
    private String code;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("closing_date")
    private String closingDate;
    private boolean isOpened;
    private String password;
    private String description;
    private List<InstructorInfoDTO> instructorsInfo;
    private List<TagDTO> tags;
    private List<DepartmentDTO> departments;
    private boolean hasQuiz;
    private QuizDTO quiz;


    public TrainingDTO() {}


    public TrainingDTO(Training entity, DateTimeFormatter formatter) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.creationDate = formatter.format(entity.getCreationDate());
        this.closingDate = formatter.format(entity.getClosingDate());
        this.isOpened = LocalDateTime.now().isBefore(entity.getClosingDate());
        this.password = entity.getPassword();
        this.description = entity.getDescription();
        this.instructorsInfo = entity.getInstructors().stream().map(InstructorInfoDTO::new).collect(Collectors.toList());
        this.tags = entity.getTags().stream().map(TagDTO::new).collect(Collectors.toList());
        this.departments = entity.getDepartments().stream().map(DepartmentDTO::new).collect(Collectors.toList());
        this.hasQuiz = entity.getHasQuiz();
        if (entity.getQuiz() != null) {
            this.quiz = new QuizDTO(entity.getQuiz());
        }
    }

    public TrainingDTO(Training entity) {
        this(entity, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss,SSS"));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InstructorInfoDTO> getInstructorsInfo() {
        return instructorsInfo;
    }

    public void setInstructorsInfo(List<InstructorInfoDTO> instructorsInfo) {
        this.instructorsInfo = instructorsInfo;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDTO> departments) {
        this.departments = departments;
    }

    public boolean isHasQuiz() {
        return hasQuiz;
    }

    public void setHasQuiz(boolean hasQuiz) {
        this.hasQuiz = hasQuiz;
    }

    public QuizDTO getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDTO quiz) {
        this.quiz = quiz;
    }
}
