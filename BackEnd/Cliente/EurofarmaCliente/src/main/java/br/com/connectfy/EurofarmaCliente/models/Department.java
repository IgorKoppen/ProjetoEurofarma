package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depart_name", nullable = false)
    private String departName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Role> roles;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    private Set<Training> trainings;


    public Department() {
    }

    public Department(Long id, String departName, Set<Role> roles, Set<Training> trainings) {
        this.id = id;
        this.departName = departName;
        this.roles = roles;
        this.trainings = trainings;
    }

    public Department(DepartmentDTO departmentDTO) {
        this.id = departmentDTO.getId();
        this.departName = departmentDTO.getDepartName();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDepartName() {
        return departName;
    }
    public void setDepartName(String departName) {
        this.departName = departName;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(departName, that.departName) && Objects.equals(roles, that.roles) && Objects.equals(trainings, that.trainings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
