package Enterprise;

import java.time.LocalDate;

public abstract class Employee implements Comparable<Employee> {

    /*
     * Employee's id
     */
    private int id;

    /*
     * Class field that allows to create unique ids
     */
    private static int nextId = 0;

    /*
     * Employee's name (max. 70 chars)
     */
    private String name;

    /*
     * Employee's street
     */
    private String street;

    /*
     * Employee's salary (in range [13300, 60000])
     */
    //private double salary;

    /*
     * Employee's email (contains '@')
     */
    private String email;

    /*
     * Employee's birth Year (>= 18 years old)
     */
    private int birthYear;


    private EducationLevel level;


    private int workload = 0;

    private Department department;

    /*
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
        this("Lorem Ipsum", "Sesame Street",/* 50000,*/ "lorem@uoc.edu", 1982, EducationLevel.UNDEFINED);
    }

    /*
     * Parameterized constructor
     *
     * @param name      Employee's name (max. 70 chars)
     * @param street    Employee's street
     * @param salary    Employee's salary (in range [13300, 60000])
     * @param email     Employee's email (contains '@')
     * @param birthYear Employee's year born (>= 18 years old)
     * @throws Exception when name has more than 70 chars, or salary is out of [13300, 50000], or email not contains '@' or birthYear is smaller than 18
     */
    public Employee(String name, String street, /*double salary,*/ String email, int birthYear, EducationLevel level) throws EmployeeException {
        setName(name);
        setStreet(street);
        setEmail(email);
        setBirthYear(birthYear);
        setLevel(level);
        setId();
    }

    /*
     * Getter of "id"
     *
     * @return Value of the field "id"
     */
    public int getId() {
        return id;
    }

    /*
     * Setter of "id". It assigns "nextId" value to "id" and increases "nextId" by using "incNextId()".
     */
    private void setId() {
        this.id = getNextId();
        incNextId();
    }

    /*
     * Getter of "nextId"
     *
     * @return nextId's value
     */
    public static int getNextId() {
        return nextId;
    }

    /*
     * Increases one unit the value of "nextId"
     */
    private void incNextId() {
        nextId++;
    }

    /*
     * Getter of "name"
     *
     * @return Employee's name
     */
    public String getName() {
        return name;
    }

    /*
     * Setter of "name"
     *
     * @param name New name that we want to assign to the employee
     * @throws Exception When the new name has more than 50 characters
     */
    public void setName(String name) throws EmployeeException {
        if (name.length() > 50) {
            throw new EmployeeException(EmployeeException.MSG_ERR_NAME);
        } else {
            this.name = name;
        }
    }

    /*
     * Getter of "street"
     *
     * @return Employee's street
     */
    public String getStreet() {
        return street;
    }

    /*
     * Setter of "street"
     *
     * @param street New street that we want to assign to the employee
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /*
     * Getter of "email"
     *
     * @return Employee's email
     */
    public String getEmail() {
        return email;
    }

    /*
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

    /*
     * Getter of "birthYear"
     *
     * @return Employee's birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    /*
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


    public EducationLevel getLevel() {
        return level;
    }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;

        if (this == employee ||
                (this.getName() == employee.getName() &&
                        this.getStreet() == employee.getStreet() &&
                        this.getEmail().equals(employee.getEmail()) &&
                        this.getBirthYear() == employee.getBirthYear() &&
                        this.getLevel().equals(employee.getLevel()))
        ) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nAge: " + (LocalDate.now().getYear() - getBirthYear()) + "\nEmail: " + getEmail() + "\nEducation level: " + getLevel();
    }


    public int getWorkload() {
        return workload;
    }

    public void resetWorkload() {
        workload = 0;
    }

    protected void addWorkload(int workload) throws EmployeeException {

        this.workload += workload;

        if (this.workload > 100) {
            this.workload -= workload;
            throw new EmployeeException(EmployeeException.MSG_ERR_WORKLOAD);
        }
    }


    /*
     * NOTAS: ordena de mayor a menor
     * Primero el de mayor edad
     * Si la edad coincide, por orden alfabetico del nombre.
     */
    @Override
    public int compareTo(Employee employee) {
        int result = 0;

        result = this.getBirthYear() - employee.getBirthYear();
        result = (result == 0 ? (int) (this.getName().compareTo(employee.getName())) : result);

        return result;
    }

    public abstract void todoTask(Task task) throws EmployeeException;// no implementation here

    public abstract String presentation();// no implementation here

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {

        if (department != null) {
            department.add(this);
        }

        if (this.department != null && this.department != department && this.department.exists(this)) {
            this.department.remove(this);
        }

        this.department = department;
    }


}