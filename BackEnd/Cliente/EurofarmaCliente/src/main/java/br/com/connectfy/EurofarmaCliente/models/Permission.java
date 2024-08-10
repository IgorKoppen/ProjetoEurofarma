package br.com.connectfy.EurofarmaCliente.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_permission")
public class Permission implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "permissions")
    private List<Employee> employees;

    public Permission() {}

    public Permission(Long id, String description, List<Employee> employees) {
        this.id = id;
        this.description = description;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
