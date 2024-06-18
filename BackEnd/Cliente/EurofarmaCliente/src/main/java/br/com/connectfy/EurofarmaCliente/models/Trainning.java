package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_trainning")
public class Trainning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private Date creationDate;
    private Date closingDate;
    private boolean  status;
    private int password;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "training_instructors",
            joinColumns = {@JoinColumn(name = "id_training")},
            inverseJoinColumns = {@JoinColumn(name = "id_intructor")}
    )
    private List<Trainning> instructor;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Trainning> getInstructor() {
        return instructor;
    }

    public void setInstructor(List<Trainning> instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainning trainning = (Trainning) o;
        return status == trainning.status && password == trainning.password && Objects.equals(id, trainning.id) && Objects.equals(name, trainning.name) && Objects.equals(code, trainning.code) && Objects.equals(creationDate, trainning.creationDate) && Objects.equals(closingDate, trainning.closingDate) && Objects.equals(description, trainning.description) && Objects.equals(instructor, trainning.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, creationDate, closingDate, status, password, description, instructor);
    }
}
