package com.barberia.app.dto;

import com.barberia.app.models.Employee;
import com.barberia.app.models.Turn;

import java.util.List;

public class EmployeeAndTurnDto {
    private Employee employee;
    private int numberOfTurns;

    public EmployeeAndTurnDto() {
    }

    public EmployeeAndTurnDto(Employee employee, int numberOfTurns) {
        this.employee = employee;
        this.numberOfTurns = numberOfTurns;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }
}
