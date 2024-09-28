package br.com.connectfy.EurofarmaCliente.models;

public class ProcessedEmployeeResult {
    private final Employee employee;
    private final int rowIndex;

    public ProcessedEmployeeResult(Employee employee, int rowIndex) {
        this.employee = employee;
        this.rowIndex = rowIndex;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
