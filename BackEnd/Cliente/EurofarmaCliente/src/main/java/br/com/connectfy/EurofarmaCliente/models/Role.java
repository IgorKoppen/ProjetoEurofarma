package br.com.connectfy.EurofarmaCliente.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;

    public Role() {
    }

    public Role(Long id, String roleName, Employee employee) {
        this.id = id;
        this.roleName = roleName;
        this.employee = employee;
    }

    public Role(String s) {
        this.roleName = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(roleName, role.roleName) && Objects.equals(employee, role.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, employee);
    }
}
