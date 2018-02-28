package com.codurance;

import java.math.BigDecimal;
import java.util.Objects;

public class SalarySlip {
    private final int employeeId;
    private final String employeeName;
    private final BigDecimal monthlyGrossSalary;
    private final BigDecimal nationalInsuranceContribution;
    private final BigDecimal taxFreeAllowance;
    private final BigDecimal taxableIncome;

    public SalarySlip(int employeeId, String employeeName, BigDecimal monthlymonthlyGrossSalary, BigDecimal nationalInsuranceContribution, BigDecimal taxFreeAllowance, BigDecimal taxableIncome) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.monthlyGrossSalary = monthlymonthlyGrossSalary;
        this.nationalInsuranceContribution = nationalInsuranceContribution;
        this.taxFreeAllowance = taxFreeAllowance;
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal nationalInsurance() {
        return nationalInsuranceContribution;
    }

    public BigDecimal taxFreeAllowance() {
        return taxFreeAllowance;
    }

    public BigDecimal taxableIncome() {
        return taxableIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalarySlip that = (SalarySlip) o;
        return employeeId == that.employeeId &&
                Objects.equals(employeeName, that.employeeName) &&
                Objects.equals(monthlyGrossSalary, that.monthlyGrossSalary) &&
                Objects.equals(nationalInsuranceContribution, that.nationalInsuranceContribution);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, employeeName, monthlyGrossSalary, nationalInsuranceContribution);
    }

    @Override
    public String toString() {
        return "SalarySlip{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", monthlyGrossSalary=" + monthlyGrossSalary +
                ", nationalInsuranceContribution=" + nationalInsuranceContribution +
                '}';
    }
}
