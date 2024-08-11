package br.com.connectfy.EurofarmaCliente.dtos.permission;

import br.com.connectfy.EurofarmaCliente.models.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;


    public PermissionDTO(Permission entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
