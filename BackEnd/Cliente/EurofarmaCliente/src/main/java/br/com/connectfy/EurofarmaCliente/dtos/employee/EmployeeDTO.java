package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.dtos.role.RoleInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.department.DepartmentInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


public class   EmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;
    @JsonProperty("user_name")
    @NotBlank(message = "O campo nao pode ser nulo")
    @Size(min = 4, message = "O username deve ter pelo menos 4 letras")
    private final String userName;

    @NotBlank(message = "O campo nao pode ser nulo")
    private final String name;

    @NotBlank(message = "O campo nao pode ser nulo")
    private final String surname;

    @JsonIgnore
    @NotBlank(message = "O campo nao pode ser nulo")
    private String password;

    @JsonProperty("cellphone_number")
    @NotBlank(message = "O campo não pode ser nulo")
    private final String cellphoneNumber;

    @JsonIgnore
    @NotBlank(message = "O campo nao pode ser nulo")
    @NotNull(message = "O campo nao pode ser nulo")
    private boolean accountNonExpired;

    @JsonIgnore
    @NotNull(message = "O campo nao pode ser nulo")
    private boolean credentialsNonExpired;

    @NotNull(message = "O campo nao pode ser nulo")
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean enabled;

    private final List<RoleInfoDTO> roles;

    @JsonIgnoreProperties({"employees"})
    private final DepartmentInfoDTO department;


    private final Long instructorIds;


    public EmployeeDTO(Employee entity) {
        this.id = entity.getId();
        this.userName = entity.getUsername();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.password = entity.getPassword();
        this.accountNonExpired = entity.isAccountNonExpired();
        this.credentialsNonExpired = entity.isCredentialsNonExpired();
        this.accountNonLocked = entity.isAccountNonLocked();
        this.enabled = entity.isEnabled();
        this.cellphoneNumber = entity.getCellphoneNumber();
        this.roles = entity.getList().stream().map(RoleInfoDTO::new).toList();
        this.department = new DepartmentInfoDTO(entity.getDepartments());
        this.instructorIds = entity.getInstructor().getId();
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

    public Long getInstructorIds() {
        return instructorIds;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
