package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departName;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department(){

    }
    public Department(String departName){
        this.departName = departName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDepartName() {
        return departName;
    }
    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
