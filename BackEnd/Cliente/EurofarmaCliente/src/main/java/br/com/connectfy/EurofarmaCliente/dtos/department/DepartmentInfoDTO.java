package br.com.connectfy.EurofarmaCliente.dtos.department;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUserProfileInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    @Size(min = 3, message = "Um departamento deve ter no mínimo 3 dígitos")
    @NotBlank(message = "Campo nome departamento não pode ser vazio")
    private  String departName;

    private final List<EmployeeUserProfileInfoDTO> employees = new ArrayList<>();

    public DepartmentInfoDTO(Department entity) {
        this.id = entity.getId();
        this.departName = entity.getDepartName();
        if(entity.getEmployees() != null) {
            entity.getEmployees().forEach(employee -> this.employees.add(new EmployeeUserProfileInfoDTO(employee)));
        }
    }


    public Long getId() {
        return id;
    }

    public String getDepartName() {
        return departName;
    }

    public List<EmployeeUserProfileInfoDTO> getEmployees() {
        return employees;
    }
}
