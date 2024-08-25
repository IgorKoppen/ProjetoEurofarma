package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleWithEmployeeDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;


    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Role() {
    }
    public Role(RoleDTO roleDTO){
        this.id = roleDTO.getId();
        this.roleName = roleDTO.getRoleName();
    }


    public Role(Long id, String roleName, List<Employee> employees) {
        this.id = id;
        this.roleName = roleName;
        this.employees = employees;
    }

    public Role(RoleWithEmployeeDTO dto) {
        this.id = dto.getId();
        this.roleName = dto.getRoleName();
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(id, role.id) && Objects.equals(roleName, role.roleName) && Objects.equals(employees, role.employees) && Objects.equals(department, role.department);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
