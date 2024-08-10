package br.com.connectfy.EurofarmaCliente.dtos.role;

import br.com.connectfy.EurofarmaCliente.models.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  Long id;
    @JsonProperty("role_name")
    @NotBlank(message = "O campo não pode ser nulo")
    private final String roleName;

    public RoleInfoDTO(Role entity) {
        this.id = entity.getId();
        this.roleName = entity.getRoleName();
    }

    public RoleInfoDTO(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

}
