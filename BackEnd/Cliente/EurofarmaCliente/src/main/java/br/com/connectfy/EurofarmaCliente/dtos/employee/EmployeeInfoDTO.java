package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.dtos.permission.PermissionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.role.RoleDTO;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  Long id;
    @JsonProperty("employee_registration")
    private  Long employeeRegistration;

    private  String name;

    private  String surname;

    @JsonProperty("cellphone_number")
    private  String cellphoneNumber;

    private  boolean enabled;

    private RoleDTO role = null;

    private  Long instructorId = null;

    @JsonIgnoreProperties({"employees"})
    private final  List<PermissionDTO> permission = new ArrayList<>();

    public EmployeeInfoDTO(Employee entity) {
        this.id = entity.getId();
        this.employeeRegistration = entity.getEmployeeRegistration();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.enabled = entity.isEnabled();
        this.cellphoneNumber = entity.getCellphoneNumber();
        if(entity.getInstructor() != null){
            this.instructorId = entity.getInstructor().getId();
        }
        if(entity.getPermissions() != null) {
            entity.getPermissions().forEach(permission -> this.permission.add(new PermissionDTO(permission)));
        }
        if(entity.getRoleEntity() != null){
           this.role = new RoleDTO(entity.getRoleEntity());
        }
    }

    public EmployeeInfoDTO() {
    }

    public List<PermissionDTO> getPermission() {
        return permission;
    }

    public List<String> getPermissionsDescription() {
        List<String> permissionsString = new ArrayList<>();
        this.permission.forEach(permission -> permissionsString.add(permission.getDescription()));
        return permissionsString;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeRegistration() {
        return employeeRegistration;
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

    public RoleDTO getRole() {
        return role;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
