package Enterprise.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import Enterprise.*;

@TestInstance(Lifecycle.PER_CLASS)

class SalariedEmployeeTest {

    SalariedEmployee employee;

    @BeforeAll
    void testSalariedEmployee() {
        try {
            employee = new SalariedEmployee(30000);
            assertEquals("Lorem Ipsum", employee.getName());
            assertEquals(EducationLevel.UNDEFINED, employee.getLevel());

            employee = new SalariedEmployee("Perico", "Av. Diagonal", "fmanezsa@uoc.edu", 1982, EducationLevel.MASTER, 13300);
            assertEquals("Perico", employee.getName());
            assertEquals(13300, employee.getSalary());

            employee = new SalariedEmployee("Fran", "Av. Diagonal", "fmanezsa@uoc.edu", 1982, EducationLevel.MASTER, 42000);
            assertEquals("Fran", employee.getName());
            assertEquals(42000, employee.getSalary());

        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testSalariedEmployee failed");
        }
    }

    @Test
    void testTodoTask() {

        assertEquals(0, employee.getWorkload());
        Task task1 = new Task("T01: tarea 1", 80,TaskType.FUNCTIONAL);
        Task task2 = new Task("T02: tarea 2", 10,TaskType.TECHNICAL);
        Task task3 = new Task("T03: tarea 3", 10,TaskType.ISSUE);
        Task task4 = new Task("T04: tarea 4", 5,TaskType.MANAGEMENT);

        try{
            employee.todoTask(task1);
            assertEquals(80, employee.getWorkload());
            employee.todoTask(task2);
            assertEquals(90, employee.getWorkload());
            employee.todoTask(task3);
            assertEquals(100, employee.getWorkload());
            EmployeeException ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task4));
            assertEquals(EmployeeException.MSG_ERR_WORKLOAD, ex.getMessage());
            assertEquals(100, employee.getWorkload());
        }catch(EmployeeException e) {
            fail("testTodoTask failed");
        }

    }

    @Test
    void testPresentation() {
        assertEquals("I'm a salaried employee", employee.presentation());
        assertEquals("I'm a salaried employee, and my salary is: 42000.0", employee.presentation(true));
        assertEquals("I'm a salaried employee, and not show my salary", employee.presentation(false));
    }

    @Test
    void testGetPaymentDay() {
        assertEquals(5, employee.getPaymentDay());
    }

    @Test
    void testCalculateSalary() {
        assertEquals(42000.00, employee.calculateSalary());
    }

    @Test
    void testIncomeTax() {
        assertEquals(16800.00, employee.calculateIncomeTax());
    }

    @Test
    void testPensionContribution() {
        assertEquals(3150.00, employee.pensionContribution());
    }

    @Test
    void testToString() {
        assertEquals("Salaried Employee - Name: Fran\nAge: 40\nEmail: fmanezsa@uoc.edu\nEducation level: MASTER", employee.toString());
    }

}