package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "cellphone_number",nullable = false)
    private String cellphoneNumber;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee")
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id_employee")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )
    private List<Permission> permissions;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_training", joinColumns = {@JoinColumn(name = "id_employee")},
            inverseJoinColumns = {@JoinColumn(name = "id_training")}
    )
    private List<Trainning> trainnings;

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

    public Employee(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.id();
        this.userName = employeeDTO.userName();
        this.name = employeeDTO.name();
        this.surname = employeeDTO.surname();
        this.password = employeeDTO.password();
        this.cellphoneNumber = employeeDTO.cellphoneNumber();
        this.roles = employeeDTO.roles();
        this.department = employeeDTO.department();
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

    public List<Role> getList() {
        return roles;
    }

    public void setList(List<Role> roles) {
        this.roles = roles;
    }

    public Department  getDepartments() {
        return department;
    }

    public void setDepartments(Department  department) {
        this.department = department;
    }

    public List<Trainning> getTrainnings() {
        return trainnings;
    }

    public void setTrainnings(List<Trainning> trainnings) {
        this.trainnings = trainnings;
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
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return accountNonExpired == employee.accountNonExpired && credentialsNonExpired == employee.credentialsNonExpired && accountNonLocked == employee.accountNonLocked && enabled == employee.enabled && Objects.equals(id, employee.id) && Objects.equals(userName, employee.userName) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(password, employee.password) && Objects.equals(cellphoneNumber, employee.cellphoneNumber) && Objects.equals(roles, employee.roles) && Objects.equals(department, employee.department) && Objects.equals(permissions, employee.permissions) && Objects.equals(instructor, employee.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, name, surname, password, cellphoneNumber, accountNonExpired, credentialsNonExpired, accountNonLocked, enabled, roles, department, permissions, instructor);
    }
}
