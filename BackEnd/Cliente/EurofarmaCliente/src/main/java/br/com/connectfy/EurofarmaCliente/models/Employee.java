package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "tb_employees")
public class Employee implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private String password;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
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


    public Employee() {}

    public Employee(Long id, String name, String surname, String password,
                    boolean accountNonExpired,
                    boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    boolean enabled, List<Role> roles, Department  department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.roles = roles;
        this.department = department;
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

    @Override
    public String getUsername() {
        return this.userName;
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
    public boolean isEnabled() {
        return this.enabled;
    }
}
