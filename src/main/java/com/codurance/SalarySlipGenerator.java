package com.codurance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlipGenerator {

    public static final BigDecimal NIC_MINIMUM_SALARY_TAXABLE = new BigDecimal("8060");

    SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee.id(), employee.name(),
                monthlyValue(employee.grossSalary()),
                monthlyValue(computeNationalInsuranceContribution(employee.grossSalary())),
                new BigDecimal("916.67"), new BigDecimal("83.33"));
    }

    private BigDecimal monthlyValue(BigDecimal yearlyValue) {
        return yearlyValue.divide(new BigDecimal("12"), 2, RoundingMode.CEILING);
    }

    private static BigDecimal computeNationalInsuranceContribution(BigDecimal yearlySalary) {
        if (yearlySalary.compareTo(NIC_MINIMUM_SALARY_TAXABLE) > 0){
            return yearlySalary.subtract(NIC_MINIMUM_SALARY_TAXABLE).multiply(new BigDecimal("12")).divide(new BigDecimal("100"));
        }
        return BigDecimal.ZERO;
    }

}
