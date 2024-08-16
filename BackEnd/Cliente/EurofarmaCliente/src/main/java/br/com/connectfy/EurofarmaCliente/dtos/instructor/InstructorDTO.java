package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.models.Instructor;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class InstructorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private  Long id;

    @NotNull(message = "Campo não pode ser vazio")
    private  EmployeeInfoDTO employee;

    public InstructorDTO(Instructor entity) {
        this.id = entity.getId();
        if(entity.getEmployee() != null) {
            this.employee = new EmployeeInfoDTO(entity.getEmployee());
        }
    }

    public InstructorDTO(EmployeeInfoDTO employee, List<TrainingDTO> trainings) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public EmployeeInfoDTO getEmployee() {
        return employee;
    }

}
