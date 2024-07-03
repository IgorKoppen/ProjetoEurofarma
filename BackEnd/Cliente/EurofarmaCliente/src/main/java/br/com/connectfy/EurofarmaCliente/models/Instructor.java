package br.com.connectfy.EurofarmaCliente.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne(mappedBy = "instructor")
    private Employee employee;

    @JsonBackReference
    @ManyToMany(mappedBy = "instructors")
    private List<Trainning> trainnings;

    public Instructor() {
    }

    public Instructor(Employee employee, List<Trainning> trainnings) {
        this.employee = employee;
        this.trainnings = trainnings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Trainning> getTrainnings() {
        return trainnings;
    }

    public void setTrainnings(List<Trainning> transactions) {
        this.trainnings = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(trainnings, that.trainnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, trainnings);
    }
}
