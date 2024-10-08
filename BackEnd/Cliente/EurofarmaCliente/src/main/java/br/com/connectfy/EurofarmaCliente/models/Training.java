package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "tb_trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime  creationDate;

    @Column(nullable = false)
    private LocalDateTime closingDate;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_instructors",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_instructor")}
    )
    private Set<Instructor> instructors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_tags",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")}
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "training", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<EmployeeTraining> employeeTrainings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "training_departments",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_department")}
    )
    private Set<Department> departments;

    @Column(nullable = false)
    private Boolean hasQuiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    public Training(){}


    public Training(TrainingDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.code = dto.getCode();
        this.creationDate = LocalDateTime.parse(dto.getCreationDate());
        this.closingDate = LocalDateTime.parse(dto.getClosingDate());
        this.password = dto.getPassword();
        this.description = dto.getDescription();
        this.tags = dto.getTags().stream().map(Tag::new).collect(Collectors.toSet());
        this.departments = dto.getDepartments().stream().map(Department::new).collect(Collectors.toSet());
        this.hasQuiz = dto.isHasQuiz();
        if(dto.getQuiz() != null){
            this.quiz = new Quiz(dto.getQuiz());
        }
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

    public LocalDateTime  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime  creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime  getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime  closingDate) {
        this.closingDate = closingDate;
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


    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<EmployeeTraining> getEmployees() {
        return employeeTrainings;
    }

    public void setEmployees(Set<EmployeeTraining> employeeTrainings) {
        this.employeeTrainings = employeeTrainings;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<EmployeeTraining> getEmployeeTrainings() {
        return employeeTrainings;
    }

    public void setEmployeeTrainings(Set<EmployeeTraining> employeeTrainings) {
        this.employeeTrainings = employeeTrainings;
    }

    public Boolean getHasQuiz() {
        return hasQuiz;
    }

    public void setHasQuiz(Boolean hasQuiz) {
        this.hasQuiz = hasQuiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id) && Objects.equals(name, training.name) && Objects.equals(code, training.code) && Objects.equals(creationDate, training.creationDate) && Objects.equals(closingDate, training.closingDate) && Objects.equals(password, training.password) && Objects.equals(description, training.description) && Objects.equals(instructors, training.instructors) && Objects.equals(tags, training.tags) && Objects.equals(employeeTrainings, training.employeeTrainings) && Objects.equals(departments, training.departments) && Objects.equals(hasQuiz, training.hasQuiz) && Objects.equals(quiz, training.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
