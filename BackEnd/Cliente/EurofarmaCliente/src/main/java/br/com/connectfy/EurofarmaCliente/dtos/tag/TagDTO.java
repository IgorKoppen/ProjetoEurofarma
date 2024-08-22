package br.com.connectfy.EurofarmaCliente.dtos.tag;

import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class TagDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    @Size(min = 3, message = "O nome da tag deve ter no mínimo 3 caracteres.")
    private final String name;

    @NotBlank(message = "A cor é obrigatória e não pode estar em branco.")
    @Size(min = 7, max = 7, message = "A cor deve ter exatamente 7 caracteres (incluindo o #).")
    private final String color;

    public TagDTO(Tag entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.color = entity.getColor();
    }

    public TagDTO(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}
