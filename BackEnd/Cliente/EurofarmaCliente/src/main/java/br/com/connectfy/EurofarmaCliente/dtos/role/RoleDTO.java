package br.com.connectfy.EurofarmaCliente.dtos.role;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.models.Role;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.Serial;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String roleName;

    private DepartmentDTO department;


    public RoleDTO(Role entity) {
        this.id = entity.getId();
        this.roleName = entity.getRoleName();
        if (entity.getDepartment() != null) {
            this.department = new DepartmentDTO(entity.getDepartment());
        }
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
}