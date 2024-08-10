package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_employees")
public class Employee implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @Column(nullable = false)
    private String password;

    @Column(name = "cellphone_number", nullable = false,unique = true)
    private String cellphoneNumber;


    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;


    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;


    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean enabled;


    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    private List<Role> roles;


    @JsonManagedReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Department department;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id_employee",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )
    private List<Permission> permissions = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;



    @OneToMany(mappedBy = "employee")
    private List<EmployeeTraining> employeeTrainings;

    public Employee() {

    }

    public Employee(Long id, String userName, String name, String surname, String password,
                    String cellphoneNumber, boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked, boolean enabled, List<Role> roles, Department department,
                    List<Permission> permissions, Instructor instructor) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.roles = roles;
        this.department = department;
        this.permissions = permissions;
        this.instructor = instructor;
    }


    public Employee(EmployeeInsertDTO dto) {
        this.name = dto.name();
        this.surname = dto.surname();
        this.cellphoneNumber = dto.cellphoneNumber();
    }

    public Employee(EmployeeInfoDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.userName = employeeDTO.getUserName();
        this.name = employeeDTO.getName();
        this.surname = employeeDTO.getSurname();
        this.cellphoneNumber = employeeDTO.getCellphoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }


    public String getUserName() {
        return userName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
   public void addPermission(Permission permission){
        this.permissions.add(permission);
   }
   public void addPermissionsIds(List<Long> ids){
        for(Long id : ids){
            Permission permission = new Permission();
            permission.setId(id);
            this.permissions.add(permission);
        }

   }
    public List<Role> getList() {
        return roles;
    }

    public void setList(List<Role> roles) {
        this.roles = roles;
    }


    public List<EmployeeTraining> getEmployeeTrainings() {
        return employeeTrainings;
    }

    public void setEmployeeTrainings(List<EmployeeTraining> trainings) {
        this.employeeTrainings = trainings;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return accountNonExpired == employee.accountNonExpired && credentialsNonExpired == employee.credentialsNonExpired && accountNonLocked == employee.accountNonLocked && enabled == employee.enabled && Objects.equals(id, employee.id) && Objects.equals(userName, employee.userName) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(password, employee.password) && Objects.equals(cellphoneNumber, employee.cellphoneNumber) && Objects.equals(roles, employee.roles) && Objects.equals(department, employee.department) && Objects.equals(permissions, employee.permissions) && Objects.equals(instructor, employee.instructor) && Objects.equals(employeeTrainings, employee.employeeTrainings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
