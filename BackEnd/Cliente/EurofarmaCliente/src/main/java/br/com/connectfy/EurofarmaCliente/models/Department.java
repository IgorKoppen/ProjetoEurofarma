package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<Employee> employees = new ArrayList<>();

    public Department(){

    }
    public Department(String departName){
        this.departName = departName;
    }

    public Department(DepartmentDTO departmentDTO) {
        this.id = departmentDTO.id();
        this.departName = departmentDTO.departName();
        this.employees = departmentDTO.employees();
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
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(departName, that.departName) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departName, employees);
    }
}
