package Enterprise.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import Enterprise.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)

class ComissionEmployeeWithSalaryTest {

    CommissionEmployeeWithSalary employee;
    CommissionEmployeeWithSalary employee2;

    @BeforeAll
    void testComissionEmployeeWithSalary() {
        try {
            employee = new CommissionEmployeeWithSalary(21500.90,1.5);
            assertEquals("Lorem Ipsum", employee.getName());
            assertEquals(EducationLevel.UNDEFINED, employee.getLevel());
            assertEquals(1.5, employee.getCommissionRate());
            assertEquals(0, employee.getSales());
            employee.setSales(750000);
            assertEquals(750000, employee.getSales());

            EmployeeException ex = assertThrows(EmployeeException.class, () -> new CommissionEmployeeWithSalary(20000, 11));
            assertEquals(EmployeeException.MSG_ERR_COMMISSION_RATE, ex.getMessage());

            ex = assertThrows(EmployeeException.class, () -> new CommissionEmployeeWithSalary(20000, -1));
            assertEquals(EmployeeException.MSG_ERR_COMMISSION_RATE, ex.getMessage());

            ex = assertThrows(EmployeeException.class, () -> new CommissionEmployeeWithSalary(-20000, 2));
            assertEquals(EmployeeException.MSG_ERR_NEGATIVE, ex.getMessage());

            employee2 = new CommissionEmployeeWithSalary("Perico", "Av. Diagonal", "fmanezsa@uoc.edu", 1982, EducationLevel.MASTER, 34500.00, 2);
            assertEquals("Perico", employee2.getName());
            assertEquals(2, employee2.getCommissionRate());
            assertEquals(0, employee2.getSales());
            employee2.setSales(800000);
            assertEquals(800000, employee2.getSales());

        } catch (EmployeeException e) {
            e.printStackTrace();
            fail("testComissionEmployeeWithSalary failed");
        }
    }

    @Test
    void testTodoTask() {

        assertEquals(0, employee.getWorkload());
        Task task1 = new Task("T01: tarea 1", 80,TaskType.FUNCTIONAL);
        Task task2 = new Task("T02: tarea 2", 10,TaskType.TECHNICAL);
        Task task3 = new Task("T03: tarea 3", 10,TaskType.ISSUE);
        Task task4 = new Task("T04: tarea 4", 5,TaskType.MANAGEMENT);
        Task task5 = new Task("T05: tarea 30", 90, TaskType.MANAGEMENT);

        try{
            employee.todoTask(task1);
            assertEquals(80, employee.getWorkload());

            EmployeeException ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task2));
            assertEquals(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED, ex.getMessage());
            assertEquals(80, employee.getWorkload());

            ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task3));
            assertEquals(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED, ex.getMessage());
            assertEquals(80, employee.getWorkload());

            employee.todoTask(task4);
            assertEquals(85, employee.getWorkload());

            ex = assertThrows(EmployeeException.class, () -> employee.todoTask(task5));
            assertEquals(EmployeeException.MSG_ERR_WORKLOAD, ex.getMessage());
            assertEquals(85, employee.getWorkload());
        }catch(EmployeeException e) {
            fail("testTodoTask failed");
        }

    }

    @Test
    void testPresentation() {
        assertEquals("I'm a commission employee", employee.presentation());
    }

    @Test
    void testGetPaymentDay() {
        assertEquals(10, employee.getPaymentDay());
    }

    @Test
    void testCalculateSalary() {
        assertEquals(32750.90, employee.calculateSalary(), 0.1);
        assertEquals(50500.00, employee2.calculateSalary(), 0.1);
    }

    @Test
    void testIncomeTax() throws EmployeeException {
        assertEquals(9825.27, employee.calculateIncomeTax(), 0.1);
        assertEquals(20200.00, employee2.calculateIncomeTax(), 0.1);

        CommissionEmployeeWithSalary commissionEmployeeWithSalary = new CommissionEmployeeWithSalary(34300, 2);
        commissionEmployeeWithSalary.setSales(90000);
        assertEquals(14440.00,commissionEmployeeWithSalary.calculateIncomeTax(), 0.1);

        commissionEmployeeWithSalary.setCommissionRate(2);
        commissionEmployeeWithSalary.setSales(0);
        assertEquals(10290.00,commissionEmployeeWithSalary.calculateIncomeTax(), 0.1);

        commissionEmployeeWithSalary.setSales(1000);
        assertEquals(10296.00,commissionEmployeeWithSalary.calculateIncomeTax(), 0.1);
    }

    @Test
    void testPensionContribution() throws EmployeeException {
        assertEquals(1286.64, employee.pensionContribution(), 0.1);
        assertEquals(5518.93, employee2.pensionContribution(), 0.1);

        CommissionEmployeeWithSalary commissionEmployeeWithSalary = new CommissionEmployeeWithSalary(15500, 4);
        commissionEmployeeWithSalary.setSales(304000);

        assertEquals(711.26,commissionEmployeeWithSalary.pensionContribution(), 0.1);

        commissionEmployeeWithSalary.setCommissionRate(2);
        commissionEmployeeWithSalary.setSales(0);
        commissionEmployeeWithSalary.setSalary(0);
        assertEquals(0,commissionEmployeeWithSalary.pensionContribution(), 0.1);

        commissionEmployeeWithSalary.setSales(900000);
        assertEquals(192.86,commissionEmployeeWithSalary.pensionContribution(), 0.1);

        commissionEmployeeWithSalary.setSalary(50000);
        assertEquals(13405.71,commissionEmployeeWithSalary.pensionContribution(), 0.1);
    }

    @Test
    void testBonus() {
        double result = employee.bonusExtra();
        assertThat(employee.bonusExtra(), anyOf(equalTo(0.0), equalTo(2.5)));
        assertEquals(result, employee.bonusExtra());
        //test para probar que getBonus no genera nuevos resultados
        assertEquals(result, employee.bonusExtra());
        assertEquals(result, employee.bonusExtra());
        assertEquals(result, employee.bonusExtra());
    }

    @Test
    void testToString() {
        assertEquals("Commission Employee - Name: Lorem Ipsum\nAge: 40\nEmail: lorem@uoc.edu\nEducation level: UNDEFINED", employee.toString());
    }

}
