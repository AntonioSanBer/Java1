package project.Object;

import time.LocalDate;

/**
 * @author Fran
 * @version 1.0
 * <p>
 * This class represents a employee of a company
 */
public class Employee {


    /**
     * project.Object.Employee's id
     */
    private int id;

    /**
     * Class field that allows to create unique ids
     */
    private static int nextId = 0;

    /**
     * project.Object.Employee's name (max. 50 chars)
     */
    private String name;

    /**
     * project.Object.Employee's street
     */
    private String street;

    /**
     * project.Object.Employee's salary (in range [13300, 60000])
     */
    private double salary;

    /**
     * project.Object.Employee's email (contains '@')
     */
    private String email;

    /**
     * project.Object.Employee's birth Year (>= 18 years old)
     */
    private int birthYear;

    private EducationLevel level;

    /**
     * Default constructor:
     * name = "Lorem Ipsum".
     * street = "Sesame Street"
     * salary = 50000
     * email = "lorem@uoc.edu"
     * birthYear = 1982
     *
     * @throws Exception is never thrown because the values of the fields are correct
     */
    public Employee() throws EmployeeException {
        this("Lorem Ipsum", "Sesame Street", 50000, "lorem@uoc.edu", 1982, EducationLevel.UNDEFINED);
    }

    /**
     * Parameterized constructor
     *
     * @param name      project.Object.Employee's name (max. 50 chars)
     * @param street    project.Object.Employee's street
     * @param salary    project.Object.Employee's salary (in range [13300, 60000])
     * @param email     project.Object.Employee's email (contains '@')
     * @param birthYear project.Object.Employee's year born (>= 18 years old)
     * @throws Exception when name has more than 50 chars, or salary is out of [13300, 50000], or email not contains '@' or birthYear is smaller than 18
     */
    public Employee(String name, String street, double salary, String email, int birthYear, EducationLevel level) throws EmployeeException {
        setName(name);
        setStreet(street);
        setSalary(salary);
        setEmail(email);
        setBirthYear(birthYear);
        setLevel(level);
        setId();


    }

    //public project.Object.Employee(String name1, String street1, double salary1, String email1, int birthYear1, project.Object.EducationLevel certificateHigherEducation)throws project.Object.EmployeeException {

    //}

    /**
     * Getter of "id"
     *
     * @return Value of the field "id"
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of "id". It assigns "nextId" value to "id" and increases "nextId" by using "incNextId()".
     */
    private void setId() {
        this.id = getNextId();
        incNextId();
    }

    /**
     * Getter of "nextId"
     *
     * @return nextId's value
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Increases one unit the value of "nextId"
     */
    private void incNextId() {
        nextId++;
    }

    /**
     * Getter of "name"
     *
     * @return project.Object.Employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of "name"
     *
     * @param name New name that we want to assign to the employee
     * @throws EmployeeException When the new name has more than 50 characters
     */
    public void setName(String name) throws EmployeeException {
        if (name.length() > 50) {
            throw new EmployeeException(EmployeeException.MSG_ERR_NAME);
        } else {
            this.name = name;

        }
    }

    /**
     * Getter of "street"
     *
     * @return project.Object.Employee's street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter of "street"
     *
     * @param street New street that we want to assign to the employee
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter of "salary"
     *
     * @return project.Object.Employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
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

    /**
     * Getter of "email"
     *
     * @return project.Object.Employee's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of "email"
     *
     * @param email New email that we want to assign to the employee
     * @throws Exception When the new email not contains '@'
     */
    public void setEmail(String email) throws EmployeeException {

        if (!email.contains("@")) {
            throw new EmployeeException(EmployeeException.MSG_ERR_EMAIL);
        } else {
            this.email = email;
        }
    }

    /**
     * Getter of "birthYear"
     *
     * @return project.Object.Employee's birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    public EducationLevel getLevel() {
        return level;
    }

    /**
     * Setter of "birthYear"
     *
     * @param birthYear capacity New birthYear that we want to assign to the employee
     * @throws Exception When the new birthYear is smaller than 18
     */
    public void setBirthYear(int birthYear) throws EmployeeException {
        if (birthYear > LocalDate.now().getYear() - 18) {
            throw new EmployeeException(EmployeeException.MSG_ERR_AGE);
        } else {
            this.birthYear = birthYear;
        }
   }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Name: " + getName() + '\n' + "Age: " + Integer.toString(LocalDate.now().getYear() - birthYear) + '\n' + "Email: " + getEmail() + '\n' + "Education level: " + getLevel();

    }

    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;
        if (this == employee ||
                (this.getName() == employee.getName()
                        && this.getStreet() == employee.getStreet()
                        && this.getSalary() == employee.getSalary()
                        && this.getEmail() == employee.getEmail()
                        && this.getLevel().equals(employee.getLevel()))
        ) {
            return true;
        }
        return false;
    }
}
