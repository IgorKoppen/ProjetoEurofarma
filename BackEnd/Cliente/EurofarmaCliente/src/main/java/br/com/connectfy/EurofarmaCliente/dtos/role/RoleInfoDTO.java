package br.com.connectfy.EurofarmaCliente.dtos.role;

import jakarta.validation.constraints.NotBlank;

public class RoleInfoDTO {

    private final Long id;

    @NotBlank(message = "O nome do cargo é obrigatório e não pode estar em branco.")
    private final String roleName;

    public RoleInfoDTO(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
