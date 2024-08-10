package br.com.connectfy.EurofarmaCliente.dtos.tag;

import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, message = "O nome da Tag deve ter 3 dígitos")
    private String name;
    @NotBlank(message = "A cor não pode ser vazio")
    @Size(min = 7, max = 7, message = "A cor deve ter 7 dígitos")
    private String color;

    public TagInfoDTO(Tag entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.color = entity.getColor();
    }

    public TagInfoDTO(String name, String color) {
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
