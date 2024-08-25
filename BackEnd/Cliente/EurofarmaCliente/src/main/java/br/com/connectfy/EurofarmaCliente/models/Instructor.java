package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDTO;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL,optional = false)
    private Employee employee;


    @ManyToMany(mappedBy = "instructors")
    private Set<Training> trainings;



    public Instructor() {
    }


    public Instructor(InstructorDTO dto) {
      this.id = dto.getId();
      this.employee = new Employee(dto.getEmployee());
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

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> transactions) {
        this.trainings = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(trainings, that.trainings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
