package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.models.Employee;

public class EmployeeInfoDTO {
    private final Long id;
    private final String name;
    private final String surname;
    private final String cellphoneNumber;

    public EmployeeInfoDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.cellphoneNumber = entity.getCellphoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }
}
