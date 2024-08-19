package br.com.connectfy.EurofarmaCliente.dtos.role;

import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleWithEmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private final String roleName;


    @JsonIgnoreProperties({"role"})
    private List<EmployeeInfoDTO> employees = new ArrayList<>();

    public RoleWithEmployeeDTO(Role entity) {
        this.id = entity.getId();
        this.roleName = entity.getRoleName();
        if (entity.getEmployees() != null) {
            this.employees = entity.getEmployees().stream().map(EmployeeInfoDTO::new).toList();
        }
    }

    public RoleWithEmployeeDTO(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }


    public List<EmployeeInfoDTO> getEmployees() {
        return employees;
    }
}
