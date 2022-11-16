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
class HourlyEmployeeTest {

    HourlyEmployee employee;
    HourlyEmployee employee2;

    @BeforeAll
    void testSalariedEmployee() {
        try {
            employee = new HourlyEmployee(12, 10);
            assertEquals("Lorem Ipsum", employee.getName());
            assertEquals(EducationLevel.UNDEFINED, employee.getLevel());
            assertEquals(12, employee.getWage());
            assertEquals(10, employee.getHours());

            EmployeeException ex = assertThrows(EmployeeException.class, () -> new HourlyEmployee(9, 41));
            assertEquals(EmployeeException.MSG_ERR_HOURS, ex.getMessage());

            ex = assertThrows(EmployeeException.class, () -> new HourlyEmployee(9, -1));
            assertEquals(EmployeeException.MSG_ERR_HOURS, ex.getMessage());

            ex = assertThrows(EmployeeException.class, () -> new HourlyEmployee(-9, 25));
            assertEquals(EmployeeException.MSG_ERR_NEGATIVE, ex.getMessage());

            employee2 = new HourlyEmployee("Perico", "Av. Diagonal", "fmanezsa@uoc.edu", 1982, EducationLevel.MASTER, 18, 20);
            assertEquals("Perico", employee2.getName());
            assertEquals(18, employee2.getWage());
            assertEquals(20, employee2.getHours());


        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testSalariedEmployee failed");
        }
    }

    @Test
    void testTodoTask() {

        assertEquals(0, employee.getWorkload());
        Task task1 = new Task("T01: tarea 1", 80, TaskType.FUNCTIONAL);
        Task task2 = new Task("T02: tarea 2", 10, TaskType.TECHNICAL);
        Task task3 = new Task("T03: tarea 3", 10, TaskType.ISSUE);
        Task task4 = new Task("T04: tarea 4", 5, TaskType.MANAGEMENT);
        Task task5 = new Task("T05: tarea 5", 90, TaskType.ISSUE);

        try {
            EmployeeException ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task1));
            assertEquals(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED, ex.getMessage());
            assertEquals(0, employee.getWorkload());

            employee.todoTask(task2);
            assertEquals(10, employee.getWorkload());

            employee.todoTask(task3);
            assertEquals(20, employee.getWorkload());

            ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task4));
            assertEquals(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED, ex.getMessage());
            assertEquals(20, employee.getWorkload());

            ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task5));
            assertEquals(EmployeeException.MSG_ERR_WORKLOAD, ex.getMessage());
            assertEquals(20, employee.getWorkload());
        } catch (EmployeeException e) {
            fail("testTodoTask failed");
        }

    }

    @Test
    void testPresentation() {
        assertEquals("I'm a hourly employee", employee.presentation());
    }

    @Test
    void testGetPaymentDay() {
        assertEquals(5, employee.getPaymentDay());
    }

    @Test
    void testCalculateSalary() {
        assertEquals(6000.00, employee.calculateSalary());
        assertEquals(18000.00, employee2.calculateSalary());
    }

    @Test
    void testIncomeTax() {
        assertEquals(1140.00, employee.calculateIncomeTax(), 0.1);
        assertEquals(4320.00, employee2.calculateIncomeTax(), 0.1);
    }

    @Test
    void testPensionContribution() {
        assertEquals(4.28, employee.pensionContribution(), 0.1);
        assertEquals(192.86, employee2.pensionContribution(), 0.1);
    }

    @Test
    void testToString() {
        assertEquals("Hourly Employee - Name: Lorem Ipsum\nAge: 40\nEmail: lorem@uoc.edu\nEducation level: UNDEFINED", employee.toString());
    }

}
