package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_permissions")
public class Permission implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Employee> employees;

    public Permission() {}

    public Permission(Long id, String description, Set<Employee> employees) {
        this.id = id;
        this.description = description;
        this.employees = employees;
    }

    public Permission(PermissionDTO dto){
        this.id = dto.getId();
        this.description = dto.getDescription();
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

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
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

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
