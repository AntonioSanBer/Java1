package Enterprise;

import java.util.Random;

public class CommissionEmployee extends Employee implements Payroll, Bonus {

    private double sales;                // yearly sales
    private double commissionRate;      // commission percentage (1-10)
    protected double bonus = -1;

    public CommissionEmployee(double commissionRate) throws EmployeeException {
        super();
        setSales(0);
        setCommissionRate(commissionRate);
    }

    public CommissionEmployee(String name, String street, String email, int birthYear, EducationLevel level, double commissionRate) throws EmployeeException {
        super(name, street, email, birthYear, level);
        setSales(0);
        setCommissionRate(commissionRate);
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) throws EmployeeException {

        if (commissionRate < 1 || commissionRate > 10) {
            throw new EmployeeException(EmployeeException.MSG_ERR_COMMISSION_RATE);
        } else {
            this.commissionRate = commissionRate;
        }
    }

    //from Employee abstract class
    @Override
    public void todoTask(Task task) throws EmployeeException {
        if (task.getType() == TaskType.MANAGEMENT || task.getType() == TaskType.FUNCTIONAL) {
            addWorkload(task.getWorkload());
        } else {
            throw new EmployeeException(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED);
        }
    }

    //from Employee abstract class
    @Override
    public String presentation() {
        return "I'm a commission employee";
    }

    //from Payroll Interface
    @Override
    public int getPaymentDay() {
        return 10;
    }

    //from Payroll Interface
    @Override
    public double calculateSalary() {
        return (getCommissionRate() / 100) * getSales();
    }

    //from Bonus Interface
    @Override
    public double bonusExtra() {
        if (bonus == -1) {
            if (new Random().nextFloat() > 0.6) {
                bonus = new Random().nextInt(5) + 1;
            } else {
                bonus = 0;
            }
        }
        return bonus;
    }

    @Override
    public String toString() {
        return "Commission Employee - " + super.toString();
    }

}
