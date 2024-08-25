package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.tag.TagDTO;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(mappedBy = "tags")
    private Set<Training> trainnings;

    public Tag() {
    }

    public Tag(Long id, String name, String color, Set<Training> trainings) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.trainnings = trainings;
    }

    public Tag(TagDTO dto) {
        if(dto.getId() != null) {
            this.id = dto.getId();
        }
        this.name = dto.getName();
        this.color = dto.getColor();
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

    public Set<Training> getTrainings() {
        return trainnings;
    }

    public void setTrainings(Set<Training> trainings) {
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
