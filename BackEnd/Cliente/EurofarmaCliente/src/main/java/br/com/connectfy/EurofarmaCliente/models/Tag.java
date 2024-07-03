package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.TagDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TagInsertDTO;
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

    @Column(nullable = false)
    private String color;

    @JsonBackReference
    @ManyToMany(mappedBy = "tags")
    private List<Training> trainnings;

    public Tag() {
    }

    public Tag(Long id, String name, String color, List<Training> trainnings) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.trainnings = trainnings;
    }

    public Tag(TagDTO tagDTO) {
        this.id = tagDTO.id();
        this.name = tagDTO.name();
        this.color = tagDTO.color();
        this.trainnings = tagDTO.trainings();
    }

    public Tag(TagInsertDTO tagDTO) {
        this.id = tagDTO.id();
        this.name = tagDTO.name();
        this.color = tagDTO.color();
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

    public List<Training> getTrainnings() {
        return trainnings;
    }

    public void setTrainnings(List<Training> trainnings) {
        this.trainnings = trainnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(color, tag.color) && Objects.equals(trainnings, tag.trainnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, trainnings);
    }
}
