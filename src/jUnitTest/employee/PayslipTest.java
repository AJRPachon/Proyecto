package jUnitTest.employee;

import basicsClasses.employee.Employee;
import basicsClasses.employee.Payslip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayslipTest {

    Employee emp = new Employee();
    Payslip pay = new Payslip(1600, emp);

    @Test
    void getSalary() {
        double salary = pay.getSalary();
        assertEquals(salary, 1600);
    }

    @Test
    void setSalary() {
        pay.setSalary(1850);
        double salary = pay.getSalary();
        assertEquals(salary, 1850);
    }

    @Test
    void getEmployee() {
    }

    @Test
    void setEmployee() {
    }

    @Test
    void testToString() {
    }
}