package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
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

    @Column(name = "employee_registration", unique = true, nullable = false,length = 6)
    private Long employeeRegistration;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @Column(nullable = false)
    private String password;

    @Column(name = "cellphone_number", nullable = false, unique = true)
    private String cellphoneNumber;


    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;


    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;


    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id_employee", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )
    private Set<Permission> permissions = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;


    @OneToMany(mappedBy = "employee")
    private Set<EmployeeTraining> employeeTrainings = new HashSet<>();

    public Employee() {

    }

    public Employee(Long id, Long employeeRegistration, String name, String surname, String password, String cellphoneNumber, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, boolean enabled, Role role, Set<Permission> permissions, Instructor instructor, Set<EmployeeTraining> employeeTrainings) {
        this.id = id;
        this.employeeRegistration = employeeRegistration;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.role = role;
        this.permissions = permissions;
        this.instructor = instructor;
        this.employeeTrainings = employeeTrainings;
    }

    public Employee(EmployeeInsertDTO dto) {
        this.name = dto.name();
        this.surname = dto.surname();
        this.cellphoneNumber = dto.cellphoneNumber();
        this.employeeRegistration = dto.employeeRegistration();
    }

    public Employee(EmployeeInfoDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.employeeRegistration = employeeDTO.getEmployeeRegistration();
        this.name = employeeDTO.getName();
        this.surname = employeeDTO.getSurname();
        this.cellphoneNumber = employeeDTO.getCellphoneNumber();
        this.employeeRegistration = employeeDTO.getEmployeeRegistration();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeRegistration() {
        return employeeRegistration;
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

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }




    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }


    public List<String> getPermissionRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<EmployeeTraining> getEmployeeTrainings() {
        return employeeTrainings;
    }

    public void setEmployeeTrainings(Set<EmployeeTraining> employeeTrainings) {
        this.employeeTrainings = employeeTrainings;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.employeeRegistration.toString();
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setEmployeeRegistration(Long employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
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
        return accountNonExpired == employee.accountNonExpired && credentialsNonExpired == employee.credentialsNonExpired && accountNonLocked == employee.accountNonLocked && enabled == employee.enabled && Objects.equals(id, employee.id) && Objects.equals(employeeRegistration, employee.employeeRegistration) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(password, employee.password) && Objects.equals(cellphoneNumber, employee.cellphoneNumber) && Objects.equals(role, employee.role) && Objects.equals(permissions, employee.permissions) && Objects.equals(instructor, employee.instructor) && Objects.equals(employeeTrainings, employee.employeeTrainings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
