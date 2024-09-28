package br.com.connectfy.EurofarmaCliente.dtos.department;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleWithEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.models.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;


public class DepartmentWithEmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String departName;

    private final Set<RoleWithEmployeeDTO> roles;

    public DepartmentWithEmployeeDTO(Department entity) {
        this.id = entity.getId();
        this.departName = entity.getDepartName();
        this.roles = entity.getRoles().stream().map(RoleWithEmployeeDTO::new).collect(Collectors.toSet());
    }



    public Long getId() {
        return id;
    }

    public String getDepartName() {
        return departName;
    }

    public Set<RoleWithEmployeeDTO> getRoles() {
        return roles;
    }
}
