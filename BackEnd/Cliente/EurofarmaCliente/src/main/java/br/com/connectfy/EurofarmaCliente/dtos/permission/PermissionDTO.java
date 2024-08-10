package br.com.connectfy.EurofarmaCliente.dtos.permission;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUserProfileInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Permission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "permissions")
    private List<EmployeeUserProfileInfoDTO> employees = new ArrayList<>();

    public PermissionDTO(Permission entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        if (entity.getEmployees() != null) {
            this.employees.addAll(entity.getEmployees().stream().map(EmployeeUserProfileInfoDTO::new).toList());
        }

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<EmployeeUserProfileInfoDTO> getEmployees() {
        return employees;
    }
}
