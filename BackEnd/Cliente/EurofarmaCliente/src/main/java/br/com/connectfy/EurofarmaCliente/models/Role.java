package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
}
