package br.com.connectfy.EurofarmaCliente.dtos.department;

import br.com.connectfy.EurofarmaCliente.models.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;


public class DepartmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    @Size(min = 3, message = "O nome do departamento deve ter no mínimo 3 caracteres.")
    @NotBlank(message = "O nome do departamento é obrigatório e não pode estar em branco.")
    private String departName;

    public DepartmentDTO(Long id) {
        this.id = id;
    }

    public DepartmentDTO(Department entity) {
        this.id = entity.getId();
        this.departName = entity.getDepartName();
    }

    public DepartmentDTO(Long id, String departName) {
        this.id = id;
        this.departName = departName;
    }

    public Long getId() {
        return id;
    }

    public String getDepartName() {
        return departName;
    }

}
