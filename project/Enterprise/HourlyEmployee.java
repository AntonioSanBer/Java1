package Enterprise;

public class HourlyEmployee extends Employee implements Payroll {

    private final int WEEKS_YEAR = 50;

    private double wage;        // wage per hour
    private double hours;       // hours worked for week

    public HourlyEmployee(double wage, double hours) throws EmployeeException {
        super();
        setWage(wage);
        setHours(hours);
    }

    public HourlyEmployee(String name, String street, String email, int birthYear, EducationLevel level, double wage, double hours) throws EmployeeException {
        super(name, street, email, birthYear, level);
        setWage(wage);
        setHours(hours);
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) throws EmployeeException {
        if (wage < 0) {
            throw new EmployeeException(EmployeeException.MSG_ERR_NEGATIVE);
        } else {
            this.wage = wage;
        }
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) throws EmployeeException {
        if (hours < 0 || hours > 40) {
            throw new EmployeeException(EmployeeException.MSG_ERR_HOURS);
        } else {
            this.hours = hours;
        }

    }

    //from Employee abstract class
    @Override
    public void todoTask(Task task) throws EmployeeException {
        if (task.getType() == TaskType.TECHNICAL || task.getType() == TaskType.ISSUE) {
            addWorkload(task.getWorkload());
        } else {
            throw new EmployeeException(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED);
        }
    }

    //from Employee abstract class
    @Override
    public String presentation() {
        return "I'm a hourly employee";
    }


    //from Payroll Interface
    @Override
    public int getPaymentDay() {
        return 5;
    }

    //from Payroll Interface
    @Override
    public double calculateSalary() {
        return (getWage() * getHours()) * this.WEEKS_YEAR;
    }

    @Override
    public String toString() {
        return "Hourly Employee - " + super.toString();
    }

}
