package br.com.connectfy.EurofarmaCliente.dtos;

import br.com.connectfy.EurofarmaCliente.models.Department;
import br.com.connectfy.EurofarmaCliente.models.Role;
import br.com.connectfy.EurofarmaCliente.models.Training;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record EmployeeCreateDTO(Long id,
                                @JsonProperty("user_name") @NotBlank(message = "O campo nao pode ser nulo") @Size(min = 4, message = "O username deve ter pelo menos 4 letras") String userName,
                                @NotBlank(message = "O campo nao pode ser nulo") String name,
                                @NotBlank(message = "O campo nao pode ser nulo") String surname,
                                @NotBlank(message = "O campo nao pode ser nulo") @Size(min = 8, message = "Senha minimo 8 caracteres") String password,
                                @JsonProperty("cellphone_number") @NotBlank String cellphoneNumber,
                                List<Role> roles,
                                Department department,
                                List<Training> trainings,
                                Long instructorId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
