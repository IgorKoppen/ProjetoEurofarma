package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;
    @JsonProperty("user_name")
    private final String userName;

    private final String name;

    private final String surname;

    @JsonProperty("cellphone_number")
    private final String cellphoneNumber;

    @Column(nullable = false)
    private boolean enabled;

    private final List<RoleInfoDTO> roles = new ArrayList<>();

    @JsonIgnoreProperties({"employees"})
    private  DepartmentInfoDTO department = null;


    private  Long instructorId = null;

    @JsonIgnoreProperties({"employees"})
    private final  List<PermissionDTO> permission = new ArrayList<>();

    public EmployeeInfoDTO(Employee entity) {
        this.id = entity.getId();
        this.userName = entity.getUsername();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.enabled = entity.isEnabled();
        this.cellphoneNumber = entity.getCellphoneNumber();
        if(entity.getDepartment() != null){
            this.department = new DepartmentInfoDTO(entity.getDepartment());
        }
        if(entity.getInstructor() != null){
            this.instructorId = entity.getInstructor().getId();
        }
        if(entity.getPermissions() != null) {
            entity.getPermissions().forEach(permission -> this.permission.add(new PermissionDTO(permission)));
        }
        if(entity.getList() != null){
            entity.getList().forEach(list -> this.roles.add(new RoleInfoDTO(list)));
        }
    }


    public List<PermissionDTO> getPermission() {
        return permission;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public List<RoleInfoDTO> getRoles() {
        return roles;
    }

    public DepartmentInfoDTO getDepartment() {
        return department;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
