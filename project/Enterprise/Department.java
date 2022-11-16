package Enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Department {

    private String name;
    private int size;
    private Set<Employee> employees;

    public Department(String name, int size) {
        employees = new TreeSet<>();
        setName(name);
        setSize(size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("[ERROR] Department's size cannot be 0 or a negative value!!");
        }

        if (size < getEmployees().size()) {
            throw new IllegalArgumentException("[ERROR] Department's size (" + size + ") cannot be less than the number of employees (" + getEmployees().size() + ") that are in the department!!");
        }
        this.size = size;
    }

    public boolean add(Employee employee) throws NullPointerException {

        if (getSize() == getEmployees().size()) {
            return false;
        } else {
            employee = Objects.requireNonNull(employee, "[ERROR] Employee object cannot be null!!");

            Department department = employee.getDepartment();

            if (department != null && department != this) {    //El employee pertenece a otro Department, asi pues, lo eliminamos de ese departamento
                department.remove(employee);
            }

            if (employees.add(employee)) {
                employee.setDepartment(this);
                return true;
            }
            return false;
        }
    }

    public boolean remove(Employee employee) throws NullPointerException {

        employee = Objects.requireNonNull(employee, "[ERROR] Employee object cannot be null");

        if (employees.remove(employee)) {
            employee.setDepartment(null);
            return true;
        }
        return false;
    }

    public void remove() throws NullPointerException {

        for (Object employee : employees.toArray()) {
            remove((Employee) employee);
        }

        employees.clear();
    }

    public boolean exists(Employee employee) {
        return employees.contains(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(employees);
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    @Override
    public String toString() {
        return "Department: " + getName() + " - Size: " + getSize() + " - Employees: " + employees.size();
    }

    /*
     *      STREAM METHODS
     */

    public void resetWorkload80() {
        this.employees.stream().filter(employee -> employee.getWorkload() >= 80).forEach(employee -> {
            employee.resetWorkload();
            System.out.print(employee.getId());
        });
    }

    public double averageSalary() {
        return this.employees.stream().filter(employee -> !(employee instanceof ApprenticeEmployee)).mapToDouble(employee -> ((Payroll) employee).calculateSalary()).average().getAsDouble();
    }

    public List<String> getSalariedEmployeeWithSalaryGreatherThanX(double salary) {
        return this.employees.stream().filter(employee -> employee instanceof SalariedEmployee).filter(employee -> ((SalariedEmployee) employee).getSalary() > salary).map(Employee::getName).sorted().collect(Collectors.toList());
    }
}
