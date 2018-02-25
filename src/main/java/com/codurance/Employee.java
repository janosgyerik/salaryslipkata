package com.codurance;

import java.math.BigDecimal;

public class Employee {
    private final int employeeId;
    private final String name;
    private final BigDecimal grossSalary;

    public Employee(int employeeId, String name, BigDecimal grossSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.grossSalary = grossSalary;
    }

    public int id() {
        return employeeId;
    }

    public String name() {
        return name;
    }

    public BigDecimal grossSalary() {
        return grossSalary;
    }
}
