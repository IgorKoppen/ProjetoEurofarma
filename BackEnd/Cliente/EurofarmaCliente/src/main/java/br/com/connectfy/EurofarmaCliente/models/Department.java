package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentWithRoleAndEmployeeDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depart_name", nullable = false)
    private String departName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    private List<Role> roles;


    public Department() {
    }

    public Department(DepartmentWithRoleAndEmployeeDTO dto) {
        this.id = dto.getId();
        this.departName = dto.getDepartName();
    }

    public Department(Long id, String departName, List<Role> roles) {
        this.id = id;
        this.departName = departName;
        this.roles = roles;
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


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(departName, that.departName) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
