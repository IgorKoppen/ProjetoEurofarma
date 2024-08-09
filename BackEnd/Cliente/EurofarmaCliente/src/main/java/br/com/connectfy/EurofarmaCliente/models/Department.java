package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depart_name", nullable = false)
    private String departName;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "department")
    private List<Employee> employees;

    public Department() {
    }

    public Department(DepartmentInfoDTO dto) {
        this.id = dto.getId();
        this.departName = dto.getDepartName();
    }

    public Department(DepartmentDTO dto) {
        this.departName = dto.departName();
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(departName, that.departName) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
