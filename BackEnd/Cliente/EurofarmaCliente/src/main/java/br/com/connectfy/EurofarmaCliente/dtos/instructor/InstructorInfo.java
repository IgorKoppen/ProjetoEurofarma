package br.com.connectfy.EurofarmaCliente.dtos.instructor;

import br.com.connectfy.EurofarmaCliente.models.Instructor;

public class InstructorInfo {
    private final Long id;
    private final String name;
    private final String surname;
    private final String cellphoneNumber;

    public InstructorInfo(Instructor entity) {
        this.id = entity.getId();
        this.name = entity.getEmployee().getName();
        this.surname = entity.getEmployee().getSurname();
        this.cellphoneNumber = entity.getEmployee().getCellphoneNumber();
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
