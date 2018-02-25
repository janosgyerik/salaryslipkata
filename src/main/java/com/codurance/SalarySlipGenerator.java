package com.codurance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlipGenerator {
    SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee.id(), employee.name(), employee.grossSalary().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
    }
}
