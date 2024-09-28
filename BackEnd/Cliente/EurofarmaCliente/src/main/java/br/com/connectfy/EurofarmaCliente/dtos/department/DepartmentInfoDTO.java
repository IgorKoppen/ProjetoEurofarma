package br.com.connectfy.EurofarmaCliente.dtos.department;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInfoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class DepartmentInfoDTO {

    private Long id;

    @Size(min = 3, message = "O nome do departamento deve ter no mínimo 3 caracteres.")
    @NotBlank(message = "O nome do departamento é obrigatório e não pode estar em branco.")
    private String departName;

    private List<RoleInfoDTO> roles;

    public DepartmentInfoDTO() {
    }

    public DepartmentInfoDTO(Long id, String departName, List<RoleInfoDTO> roles) {
        this.id = id;
        this.departName = departName;
        this.roles = roles;
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

    public List<RoleInfoDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleInfoDTO> roles) {
        this.roles = roles;
    }
}
