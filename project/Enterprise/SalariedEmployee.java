package Enterprise;

public class SalariedEmployee extends Employee implements Payroll {

    /*
     * Employee's salary (in range [13300, 60000])
     */
    private double salary;

    public SalariedEmployee(double salary) throws EmployeeException {
        super();
        setSalary(salary);
    }

    public SalariedEmployee(String name, String street, String email, int birthYear, EducationLevel level, double salary) throws EmployeeException {
        super(name, street, email, birthYear, level);
        setSalary(salary);
    }

    /*
     * Getter of "salary"
     *
     * @return Employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /*
     * Setter of "salary"
     *
     * @param salary New salary that we want to assign to the employee
     * @throws Exception When the new salary is out of the range [13300,60000]
     */
    public void setSalary(double salary) throws EmployeeException {
        if (salary < 13300 || salary > 60000) {
            throw new EmployeeException(EmployeeException.MSG_ERR_SALARY);
        } else {
            this.salary = salary;
        }
    }

    //from Employee abstract class
    @Override
    public void todoTask(Task task) throws EmployeeException {
        addWorkload(task.getWorkload());
    }

    //from Employee abstract class
    @Override
    public String presentation() {
        return "I'm a salaried employee";
    }

    //overloading
    public String presentation(boolean showSalary) {
        return showSalary ? this.presentation() + ", and my salary is: " + getSalary() : this.presentation() + ", and not show my salary";
    }

    //from Payroll Interface
    @Override
    public int getPaymentDay() {
        return 5;
    }

    //from Payroll Interface
    @Override
    public double calculateSalary() {
        return getSalary();
    }

    @Override
    public String toString() {
        return "Salaried Employee - " + super.toString();
    }
}
