package br.com.connectfy.EurofarmaCliente.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_trainning")
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
    private Boolean  status;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String description;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_instructors",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_instructor")}
    )
    private List<Instructor> instructors;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_tags",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")}
    )
    private List<Tag> tags;

    @JsonBackReference
    @OneToMany(mappedBy = "training", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<EmployeeTraining> employeeTrainings;

    public Training(){}


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

    public Set<EmployeeTraining> getEmployees() {
        return employeeTrainings;
    }

    public void setEmployees(Set<EmployeeTraining> employeeTrainings) {
        this.employeeTrainings = employeeTrainings;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", creationDate=" + creationDate +
                ", closingDate=" + closingDate +
                ", status=" + status +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", instructors=" + instructors +
                ", tags=" + tags +
                ", employees=" + employeeTrainings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
