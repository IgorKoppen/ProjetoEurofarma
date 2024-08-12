package br.com.connectfy.EurofarmaCliente.dtos.tag;

import br.com.connectfy.EurofarmaCliente.models.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private final String name;
    private final String color;

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
