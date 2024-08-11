package br.com.connectfy.EurofarmaCliente.dtos.department;


import br.com.connectfy.EurofarmaCliente.dtos.role.RoleWithDepartmentAndEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.models.Department;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentWithRoleAndEmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String departName;

    @JsonIgnoreProperties({"department"})
    private List<RoleWithDepartmentAndEmployeeDTO> roles = new ArrayList<>();

    public DepartmentWithRoleAndEmployeeDTO(Department entity) {
        this.id = entity.getId();
        this.departName = entity.getDepartName();
        if(entity.getRoles() != null) {
            this.roles = entity.getRoles().stream().map(RoleWithDepartmentAndEmployeeDTO::new).toList();
        }
    }


    public Long getId() {
        return id;
    }

    public String getDepartName() {
        return departName;
    }

    public List<RoleWithDepartmentAndEmployeeDTO> getRoles() {
        return roles;
    }
}
