package com.codurance;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SalarySlipGeneratorTest {

    public static final String JOHN_J_DOE = "John J Doe";
    public static final int EMPLOYEE_ID = 12345;
    private SalarySlipGenerator salarySlipGenerator;

    @Before
    public void setUp() throws Exception {
        salarySlipGenerator = new SalarySlipGenerator();
    }

    @Test
    public void test_basic_case() {
        int grossSalary = 5000;

        Employee employee = new Employee(EMPLOYEE_ID, JOHN_J_DOE, BigDecimal.valueOf(grossSalary));

        SalarySlipGenerator generator = new SalarySlipGenerator();

        SalarySlip expectedSalarySlip = new SalarySlip(EMPLOYEE_ID, JOHN_J_DOE, new BigDecimal("416.67"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));

        assertThat(generator.generateFor(employee)).isEqualTo(expectedSalarySlip);
    }

    @Test
    public void gross_annual_salary_of_9060_should_give_national_insurance_of_10() {
        Employee employee = createEmployee("9060.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.nationalInsurance()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    public void gross_annual_salary_of_10060_should_give_national_insurance_of_20() {
        Employee employee = createEmployee("10060.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.nationalInsurance()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    public void gross_annual_salary_of_11060_should_give_national_insurance_of_30() {
        Employee employee = createEmployee("11060.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.nationalInsurance()).isEqualTo(new BigDecimal("30.00"));
    }

    @Test
    public void annual_gross_salary_of_12000_should_give_tax_free_allowance_916_67() {
        Employee employee = createEmployee("12000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxFreeAllowance()).isEqualTo(new BigDecimal("916.67"));
    }

    @Test
    public void annual_gross_salary_of_13000_should_give_tax_free_allowance_916_67() {
        Employee employee = createEmployee("13000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxFreeAllowance()).isEqualTo(new BigDecimal("916.67"));
    }

    @Test
    public void annual_gross_salary_of_12000_should_give_taxable_income_83_33() {
        Employee employee = createEmployee("12000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxableIncome()).isEqualTo(new BigDecimal("83.33"));
    }

    @Test
    public void annual_gross_salary_of_13000_should_give_taxable_income_83_33() {
        Employee employee = createEmployee("13000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxableIncome()).isEqualTo(new BigDecimal("166.67"));
    }

    @Test
    public void annual_gross_salary_of_9000_should_give_taxable_income_0() {
        Employee employee = createEmployee("9000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxableIncome()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    public void annual_gross_salary_of_12000_should_give_tax_payable_16_67() {
        Employee employee = createEmployee("12000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxPayable()).isEqualTo(new BigDecimal("16.67"));
    }

    @Test
    public void annual_gross_salary_of_11000_should_give_tax_payable_0() {
        Employee employee = createEmployee("11000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxPayable()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    public void annual_gross_salary_of_45000_should_give_taxable_income_2833_33() {
        Employee employee = createEmployee("45000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxableIncome()).isEqualTo(new BigDecimal("2833.33"));
    }

    @Test
    public void annual_gross_salary_of_45000_should_give_tax_payable_600() {
        Employee employee = createEmployee("45000.00");

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxPayable()).isEqualTo(new BigDecimal("600.00"));
    }

    private Employee createEmployee(String salary) {
        return new Employee(EMPLOYEE_ID, JOHN_J_DOE, new BigDecimal(salary));
    }
}
