package com.codurance;

import java.math.BigDecimal;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SalarySlipGeneratorTest {
    @Test
    public void test_basic_case() {
        int employeeId = 12345;
        String name = "John J Doe";
        int grossSalary = 5000;

        Employee employee = new Employee(employeeId, name, BigDecimal.valueOf(grossSalary));

        SalarySlipGenerator generator = new SalarySlipGenerator();

        SalarySlip expectedSalarySlip = new SalarySlip(employeeId, name, new BigDecimal("416.67"));

        assertThat(generator.generateFor(employee)).isEqualTo(expectedSalarySlip);
    }
}
