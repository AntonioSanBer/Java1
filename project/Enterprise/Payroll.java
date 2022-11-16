package Enterprise;

public interface Payroll {

    int getPaymentDay();

    double calculateSalary();

    default double calculateIncomeTax(){
        double salary = calculateSalary();
        double incomeTax = 0;
        if(salary < 15000) incomeTax = salary * 0.19;
        else if(salary >= 15000 && salary < 22000) incomeTax = salary * 0.24;
        else if(salary >= 22000 && salary < 35000) incomeTax = salary * 0.30;
        else incomeTax = salary * 0.40;
        return incomeTax;
    }

    default double pensionContribution() {

        double monthlySalary = calculateSalary()/14;

        double pensionContribution = 0;
        double tax = 0;

        int numRanges = (int) (monthlySalary/200);

        while(numRanges>0) {
            pensionContribution += monthlySalary * tax;
            tax += 0.01;
            numRanges--;
        }

        return pensionContribution;
    }
}
