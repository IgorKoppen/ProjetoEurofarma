package br.com.connectfy.EurofarmaCliente.dtos.employee;

import br.com.connectfy.EurofarmaCliente.models.Employee;

public class EmployeeUserProfileInfoDTO {
    private final Long id;
    private final String name;
    private final String surname;
    private final String cellphoneNumber;
    private final Long employeeRegistration;

    public EmployeeUserProfileInfoDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.cellphoneNumber = entity.getCellphoneNumber();
        this.employeeRegistration = entity.getEmployeeRegistration();
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

    public Long getEmployeeRegistration() {
        return employeeRegistration;
    }
}
