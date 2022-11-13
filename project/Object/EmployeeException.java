package project.Object;

public class EmployeeException extends Exception{


    public static String MSG_ERR_NAME = "[ERROR] project.Object.Employee's name cannot be longer than 50 characters";
    public static String MSG_ERR_SALARY = "[ERROR] project.Object.Employee's salary must be in range [13300,60000]";
    public static String MSG_ERR_EMAIL = "[ERROR] project.Object.Employee's email does not have the correct format";
    public static String MSG_ERR_AGE = "[ERROR] project.Object.Employee's age must be greater than or equal to 18 years old";

    public EmployeeException() {
        super();
    }

    public EmployeeException(String msg) {
        super(msg);

    }

}
