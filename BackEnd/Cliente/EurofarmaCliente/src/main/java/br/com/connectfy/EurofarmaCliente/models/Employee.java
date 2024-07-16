package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeCreateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.EmployeeInfoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name = "cellphone_number",nullable = false)
    private String cellphoneNumber;

    @JsonIgnore
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @JsonIgnore
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @JsonIgnore
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean enabled;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee")
    private List<Role> roles;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id_employee")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}
    )
    private List<Permission> permissions;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @JsonBackReference
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeTraining> employeeTrainings;

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

    public Employee(EmployeeCreateDTO employeeDTO) {
        this.id = employeeDTO.id();
        this.userName = employeeDTO.userName();
        this.name = employeeDTO.name();
        this.surname = employeeDTO.surname();
        this.password = employeeDTO.password();
        this.cellphoneNumber = employeeDTO.cellphoneNumber();
        this.roles = employeeDTO.roles();
        this.department = employeeDTO.department();
    }

    public Employee(EmployeeInfoDTO employeeDTO) {
        this.id = employeeDTO.id();
        this.userName = employeeDTO.userName();
        this.name = employeeDTO.name();
        this.surname = employeeDTO.surname();
        this.cellphoneNumber = employeeDTO.cellphoneNumber();
        this.roles = employeeDTO.roles();
        this.department = employeeDTO.department();
        this.employeeTrainings = employeeDTO.trainings();
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

    public Set<EmployeeTraining> getEmployeeTrainings() {
        return employeeTrainings;
    }

    public void setEmployeeTrainings(Set<EmployeeTraining> trainnings) {
        this.employeeTrainings = trainnings;
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
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", cellphoneNumber='" + cellphoneNumber + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", department=" + department +
                ", permissions=" + permissions +
                ", instructor=" + instructor +
                '}';
    }
}
