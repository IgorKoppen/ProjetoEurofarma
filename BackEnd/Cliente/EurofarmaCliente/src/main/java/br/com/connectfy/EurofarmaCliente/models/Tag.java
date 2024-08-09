package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.tag.TagInfoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String color;

    @JsonBackReference
    @ManyToMany(mappedBy = "tags")
    private List<Training> trainnings;

    public Tag() {
    }

    public Tag(Long id, String name, String color, List<Training> trainings) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.trainnings = trainings;
    }

    public Tag(TagInfoDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.color = dto.getColor();
    }

    public Tag(TagDTO dto) {
        this.name = dto.name();
        this.color = dto.color();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Training> getTrainings() {
        return trainnings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainnings = trainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(color, tag.color) && Objects.equals(trainnings, tag.trainnings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
