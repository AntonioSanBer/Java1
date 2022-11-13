package project.Object.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import project.Object.Employee;
import project.Object.EducationLevel;
import project.Object.EmployeeException;


@TestInstance(Lifecycle.PER_CLASS)

class EmployeeTest {

    Employee employee;

    @BeforeAll
    void testEmployee() {
        try {
            employee = new Employee();
        } catch (Exception e) {
            e.printStackTrace();
            fail("testEmployee failed");
        }
    }

    @Test
    void testGetId() {
        assertEquals(0,employee.getId());
    }

    @Test
    void testSetId() {
        assertEquals(0,employee.getId());

        try{

            Employee employee2 = new Employee();
            assertEquals(1,employee2.getId());
            assertEquals(2,Employee.getNextId());
            Employee employee3 = new Employee();
            assertEquals(2,employee3.getId());
            assertEquals(3,Employee.getNextId());
        }catch (Exception e) {
            e.printStackTrace();
            fail("testSetId failed");
        }
    }

    @Test
    void testGetName() {
        assertEquals("Lorem Ipsum",employee.getName());
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

        Exception ex = assertThrows(Exception.class, () -> new Employee("Lorem ipsum dolor sit amet, consectetur vestibulum.", "Rambla Poblenou, 156", 13300.50, "fmanezsa@uoc.edu", 2003, EducationLevel.CERTIFICATE_HIGHER_EDUCATION));
        assertEquals(EmployeeException.MSG_ERR_NAME,ex.getMessage());

        assertEquals("Bobby",employee.getName());


    }

    @Test
    void testStreet() {
        assertEquals("Sesame Street",employee.getStreet());
        employee.setStreet("Av. Diagonal");
        assertEquals("Av. Diagonal",employee.getStreet());
    }


    @Test
    void testGetSalary() {
        assertEquals(50000,employee.getSalary());
    }

    @Test
    void testSetSalary() {
        try{
            employee.setSalary(20000);
            assertEquals(20000,employee.getSalary());
        }catch(Exception e) {
            fail("setSalary failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	employee.setSalary(13299));
        assertEquals(EmployeeException.MSG_ERR_SALARY, ex.getMessage());

        ex = assertThrows(Exception.class, () -> employee.setSalary(60000.01));
        assertEquals(EmployeeException.MSG_ERR_SALARY, ex.getMessage());

        try{
            employee.setSalary(50000);
            assertEquals(50000,employee.getSalary());
        }catch(Exception e) {
            fail("setSalary failed");
        }

    }

    @Test
    void testGetEmail() {
        assertEquals("lorem@uoc.edu",employee.getEmail());
    }

    @Test
    void testSetEmail() {
        try{
            employee.setEmail("dgarciaso@uoc.edu");
            assertEquals("dgarciaso@uoc.edu",employee.getEmail());
        }catch(Exception e) {
            fail("setEmail failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	employee.setEmail("fmanezsauoc.edu"));
        assertEquals(EmployeeException.MSG_ERR_EMAIL, ex.getMessage());

        assertEquals("dgarciaso@uoc.edu",employee.getEmail());

    }

    @Test
    void testGetBirthYear() {
        assertEquals(1982,employee.getBirthYear());
    }

    @Test
    void testSetBirthYear() {
        try{
            employee.setBirthYear(1970);
            assertEquals(1970,employee.getBirthYear());
        }catch(EmployeeException e) {
            fail("set failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	employee.setBirthYear(2004));
        assertEquals(EmployeeException.MSG_ERR_AGE, ex.getMessage());
        assertEquals(1970,employee.getBirthYear());


        try {
            employee.setBirthYear(1978);
            assertEquals(1978,employee.getBirthYear());
            employee.setBirthYear(2000);
            assertEquals(2000,employee.getBirthYear());
        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testSetBirthYear - Case 3 failed");
        }

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
        employee.setLevel(EducationLevel.STUDENT);
        assertEquals(EducationLevel.STUDENT,employee.getLevel());
        employee.setLevel(EducationLevel.UNDEFINED);
        assertEquals(EducationLevel.UNDEFINED,employee.getLevel());


    }

    @Test
    void testEquals() {
        Employee employee2 = employee, employee3;
        assertEquals(true,employee.equals(employee));
        assertEquals(true,employee2.equals(employee2));
        assertEquals(true,employee.equals(employee2));
        assertEquals(true,employee2.equals(employee));

        try {
            employee3 = new Employee();
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
        assertEquals("Name: Lorem Ipsum\nAge: 40\nEmail: lorem@uoc.edu\nEducation level: UNDEFINED",employee.toString());
    }
}