package jUnitTest.employee;

import basicsClasses.employee.Employee;
import basicsClasses.employee.Enums.EnumCategory;
import basicsClasses.employee.Enums.EnumPosition;
import basicsClasses.employee.Schedule;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee emp = new Employee("Antonio", "Ramirez", "53350963W", "dfsg54gfd8g", new GregorianCalendar(), EnumPosition.ChefThePartie, EnumCategory.Staff, "ES9325654654656534654", "Hola123", new Schedule[7]);


    @Test
    void getName() {
        String name = emp.getName();
        assertEquals(name, "Antonio");

    }

    @Test
    void getSurname() {
        String surname = emp.getSurname();
        assertEquals(surname, "Ramirez");
    }

    @Test
    void getDNI() {
        String dNI = emp.getDNI();
        assertEquals(dNI, "53350963W");
    }

    @Test
    void getnAF() {
        String nAF = emp.getnAF();
        assertEquals(nAF, "dfsg54gfd8g");
    }

    @Test
    void getBirthday() {
        GregorianCalendar birthday = new GregorianCalendar();
        assertEquals(birthday, new GregorianCalendar());
    }

    @Test
    void getPosition() {
        EnumPosition position = emp.getPosition();
        assertEquals(position, EnumPosition.ChefThePartie);
    }

    @Test
    void setPosition() {
        emp.setPosition(EnumPosition.Manager);
        EnumPosition position = emp.getPosition();
        assertEquals(position,EnumPosition.Manager);
    }

    @Test
    void getCategory() {
        EnumCategory category = emp.getCategory();
        assertEquals(category, EnumCategory.Staff);
    }

    @Test
    void setCategory() {
        emp.setCategory(EnumCategory.Administrator);
        EnumCategory category = emp.getCategory();
        assertEquals(category,EnumCategory.Administrator);
    }

    @Test
    void getBankAccountN() {
        String bA = emp.getBankAccountN();
        assertEquals(bA, "ES9325654654656534654");
    }

    @Test
    void setBankAccountN() {
        emp.setBankAccountN("ES49375348753489567349234");
        String bA = emp.getBankAccountN();
        assertEquals(bA, "ES49375348753489567349234");
    }

    @Test
    void getPassword() {
        String password = emp.getPassword();
        assertEquals(password, "Hola123");
    }

    @Test
    void setPassword() {
        emp.setBankAccountN("123Hola");
        String password = emp.getPassword();
        assertEquals(password, "123Hola");
    }

    @Test
    void getSchedule() {
    }

    @Test
    void setSchedule() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testEquals() {
    }
}