package br.com.connectfy.EurofarmaCliente.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
}
