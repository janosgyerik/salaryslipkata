package com.codurance;

import java.math.BigDecimal;

public class SalarySlip {
    private final int employeeId;
    private final String employeeName;
    private final BigDecimal grossSalary;

    public SalarySlip(int employeeId, String employeeName, BigDecimal grossSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalarySlip that = (SalarySlip) o;

        if (employeeId != that.employeeId) return false;
        if (employeeName != null ? !employeeName.equals(that.employeeName) : that.employeeName != null) return false;
        return grossSalary != null ? grossSalary.equals(that.grossSalary) : that.grossSalary == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (grossSalary != null ? grossSalary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SalarySlip{" +
            "employeeId=" + employeeId +
            ", employeeName='" + employeeName + '\'' +
            ", grossSalary=" + grossSalary +
            '}';
    }
}
