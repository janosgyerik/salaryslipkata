package com.codurance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlipGenerator {

    public static final BigDecimal NIC_MINIMUM_SALARY_TAXABLE = new BigDecimal("8060");
    public static final BigDecimal TAX_FREE_ALLOWANCE_LIMIT = new BigDecimal("11000.00");
    public static final BigDecimal TWELVE_MONTHS = new BigDecimal("12");

    SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee.id(), employee.name(),
            monthlyValue(employee.grossSalary()),
            monthlyValue(computeNationalInsuranceContribution(employee.grossSalary())),
            monthlyValue(TAX_FREE_ALLOWANCE_LIMIT),
            monthlyValue(computeTaxableIncome(employee.grossSalary())),
            monthlyValue(computeTaxPayable(employee.grossSalary())));
    }

    private BigDecimal computeTaxPayable(BigDecimal grossSalary) {

        if (grossSalary.compareTo(TAX_FREE_ALLOWANCE_LIMIT) <= 0) {
            return new BigDecimal("0.00");
        }
        if (grossSalary.compareTo(new BigDecimal("45000.00")) == 0){
            return new BigDecimal( ((45000 - 43000 )* 0.4) + (43000 - 11000) * 0.2);
        }
        return grossSalary.subtract(TAX_FREE_ALLOWANCE_LIMIT).multiply(new BigDecimal(0.2));
    }

    private BigDecimal computeTaxableIncome(BigDecimal annualGrossSalary) {
        if (annualGrossSalary.compareTo(TAX_FREE_ALLOWANCE_LIMIT) < 0) {
            return BigDecimal.ZERO;
        }
        return annualGrossSalary.subtract(TAX_FREE_ALLOWANCE_LIMIT);
    }

    private BigDecimal monthlyValue(BigDecimal yearlyValue) {
        return yearlyValue.divide(TWELVE_MONTHS, 2, RoundingMode.HALF_UP);
    }

    private static BigDecimal computeNationalInsuranceContribution(BigDecimal yearlySalary) {
        if (yearlySalary.compareTo(NIC_MINIMUM_SALARY_TAXABLE) > 0) {
            return yearlySalary.subtract(NIC_MINIMUM_SALARY_TAXABLE).multiply(new BigDecimal(0.12));
        }
        return BigDecimal.ZERO;
    }

}
