package Enterprise;

import java.util.Random;

public class CommissionEmployeeWithSalary extends CommissionEmployee {

    private double salary;

    public CommissionEmployeeWithSalary(double salary, double commissionRate) throws EmployeeException {
        super(commissionRate);
        setSalary(salary);
    }

    public CommissionEmployeeWithSalary(String name, String street, String email, int birthYear, EducationLevel level, double salary, double commissionRate) throws EmployeeException {
        super(name, street, email, birthYear, level, commissionRate);
        setSalary(salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws EmployeeException {
        if (salary < 0) {
            throw new EmployeeException(EmployeeException.MSG_ERR_NEGATIVE);
        } else {
            this.salary = salary;
        }
    }

    //overloading from method presentation from superclass CommissionEmployee
    public String presentation(boolean showSalary) {
        return showSalary ? this.presentation() + ", and my salary is " + getSalary() : this.presentation() + ", and not show my salary";
    }

    //from Payroll Interface
    @Override
    public double calculateSalary() {
        return super.calculateSalary() + getSalary();
    }

    //from Bonus Interface
    @Override
    public double bonusExtra() {
        if (bonus == -1) {
            if (new Random().nextFloat() > 0.75) {
                bonus = 2.5;
            } else {
                bonus = 0;
            }
        }
        return bonus;

    }
}

