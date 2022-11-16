package Enterprise.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import Enterprise.*;

@TestInstance(Lifecycle.PER_CLASS)

class EmployeeTest {

    Employee employee;

    @BeforeAll
    void testEmployee() {
        try {
            employee = new SalariedEmployee("Fran", "C/Begur", "fmanezsa@uoc.edu", 1982, EducationLevel.MASTER, 13300);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEmployee failed");
        }
    }

    @Test
    void testGetName() {
        assertEquals("Fran",employee.getName());
    }

    @Test
    void testSetName() {

        try{
            employee.setName("Bobby");
            assertEquals("Bobby",employee.getName());
        }catch(Exception e) {
            fail("setName failed");
        }

        assertEquals("Bobby",employee.getName());

        Exception ex = assertThrows(Exception.class, () -> new SalariedEmployee("Lorem ipsum dolor sit amet, consectetur vestibulum.", "Rambla Poblenou, 156", "fmanezsa@uoc.edu", 2003, EducationLevel.CERTIFICATE_HIGHER_EDUCATION, 20000));
        assertEquals(EmployeeException.MSG_ERR_NAME,ex.getMessage());

        assertEquals("Bobby",employee.getName());

    }

    @Test
    void testGetBirthYear() {
        assertEquals(1982,employee.getBirthYear());
    }



    @Test
    void testGetEducationLevel() {
        assertEquals(EducationLevel.UNDEFINED, employee.getLevel());
    }

    @Test
    void testSetEducationLevel() {
        employee.setLevel(EducationLevel.CERTIFICATE_HIGHER_EDUCATION);
        assertEquals(EducationLevel.CERTIFICATE_HIGHER_EDUCATION,employee.getLevel());
        employee.setLevel(EducationLevel.BACHELOR);
        assertEquals(EducationLevel.BACHELOR,employee.getLevel());
        employee.setLevel(EducationLevel.MASTER);
        assertEquals(EducationLevel.MASTER,employee.getLevel());
        employee.setLevel(EducationLevel.UNDEFINED);
        assertEquals(EducationLevel.UNDEFINED,employee.getLevel());
    }

    @Test
    void testSetLevel() {
        HourlyEmployee hourlyEmployee;

        try {
            hourlyEmployee = new HourlyEmployee(15,25);
            assertEquals(EducationLevel.UNDEFINED,hourlyEmployee.getLevel());

            hourlyEmployee.setLevel(EducationLevel.CERTIFICATE_HIGHER_EDUCATION);
            assertEquals(EducationLevel.CERTIFICATE_HIGHER_EDUCATION,hourlyEmployee.getLevel());

            hourlyEmployee.setLevel(EducationLevel.BACHELOR);
            assertEquals(EducationLevel.BACHELOR,hourlyEmployee.getLevel());

        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testSetGender failed");
        }
    }

    @Test
    void testEquals() {
        Employee employee2 = employee, employee3;
        assertEquals(true,employee.equals(employee));
        assertEquals(true,employee2.equals(employee2));
        assertEquals(true,employee.equals(employee2));
        assertEquals(true,employee2.equals(employee));

        try {
            employee3 = new SalariedEmployee("Fran", "C/Begur", "fmanezsa@uoc.edu", 1982, EducationLevel.UNDEFINED, 45000);
            assertEquals(true,employee3.equals(employee3));
            assertEquals(true,employee.equals(employee3));
            employee3.setLevel(EducationLevel.MASTER);
            assertEquals(false,employee.equals(employee3));
            assertEquals(false,employee2.equals(employee3));
        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testEquals failed");
        }
    }

    @Test
    void testToString() {
        assertEquals("Salaried Employee - Name: Fran\nAge: 40\nEmail: fmanezsa@uoc.edu\nEducation level: UNDEFINED",employee.toString());
    }

    @Test
    void testGetWorkload() {
        assertEquals(0,employee.getWorkload());
    }


    @Test
    void testCompareTo() {

        Employee [] employees = new Employee[4];
        employees[0] = employee;

        try {
            Employee employeeManu = new SalariedEmployee("Manu", "C/Begur", "manu@uoc.edu", 1970, EducationLevel.MASTER, 13300);
            Employee employeeZeus = new HourlyEmployee("Zeus", "Rambla Catalunya", "zeus@uoc.edu", 1993, EducationLevel.BACHELOR, 12, 25);
            Employee employeeAran = new HourlyEmployee("Aran", "C/picaso", "aran@uoc.edu", 1993, EducationLevel.BACHELOR, 12, 25);
            employees[1] = employeeManu;
            employees[2] = employeeZeus;
            employees[3] = employeeAran;

            /**
             * NOTAS compareTo: int compareTo(Employee employee)
             * devuelve < 0, entonces el employee que llama al metodo va primero.
             * devuelve == 0 entonces son iguales.
             * devuelve > 0, entonces el parametro pasado al metodo compareTo va primero.
             */

            Exception ex = assertThrows(NullPointerException.class, () ->	employee.compareTo(null));
            assertEquals(NullPointerException.class, ex.getClass());
            assertTrue(employee.compareTo(employeeManu) > 0); //positivo porque 1982 (employee) va despues que 1970 (employeeManu)
            assertTrue(employeeManu.compareTo(employee) < 0); //negativo porque 1970 (employeeManu) va antes que 1982 (employee)
            assertTrue(employee.compareTo(employee) == 0);

            Arrays.sort(employees);

            assertArrayEquals(new Employee[]{employeeManu,employee,employeeAran,employeeZeus},employees);


        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testCompareTo failed");
        }
    }
}

