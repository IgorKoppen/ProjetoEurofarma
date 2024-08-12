package br.com.connectfy.EurofarmaCliente.dtos.department;

import br.com.connectfy.EurofarmaCliente.models.Department;
import java.io.Serial;
import java.io.Serializable;


public class DepartmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String departName;

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
