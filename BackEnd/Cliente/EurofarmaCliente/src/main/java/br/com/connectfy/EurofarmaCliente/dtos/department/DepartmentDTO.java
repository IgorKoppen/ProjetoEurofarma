package br.com.connectfy.EurofarmaCliente.dtos.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentDTO(@Size(min = 3, message = "Um departamento deve ter no mínimo 3 dígitos")
                                  @NotBlank(message = "Campo nome departamento não pode ser vazio")
                                  String departName) {
}
