package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_trainning")
public class Trainning {

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
    private Boolean  status;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_instructors",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_instructor")}
    )
    private List<Instructor> instructors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_tags",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")}
    )
    private List<Tag> tags;

    @ManyToMany(mappedBy = "trainnings")
    private List<Employee> employees;

    public Trainning(){}

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

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainning trainning = (Trainning) o;
        return Objects.equals(id, trainning.id) && Objects.equals(name, trainning.name) && Objects.equals(code, trainning.code) && Objects.equals(creationDate, trainning.creationDate) && Objects.equals(closingDate, trainning.closingDate) && Objects.equals(status, trainning.status) && Objects.equals(password, trainning.password) && Objects.equals(description, trainning.description) && Objects.equals(instructors, trainning.instructors) && Objects.equals(tags, trainning.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, creationDate, closingDate, status, password, description, instructors, tags);
    }
}
