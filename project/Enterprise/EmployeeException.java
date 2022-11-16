package Enterprise;


public class EmployeeException extends Exception {

    /*
     * El primer atributo, serialVersionUID, es para evitar un warning de la serializacion
     */
    private static final long serialVersionUID = 1L;

    public final static String MSG_ERR_NAME = "[ERROR] Employee's name cannot be longer than 50 characters";
    public final static String MSG_ERR_SALARY = "[ERROR] Employee's salary must be in range [13300,60000]";
    public final static String MSG_ERR_EMAIL = "[ERROR] Employee's email does not have the correct format";
    public final static String MSG_ERR_AGE = "[ERROR] Employee's age must be greater than or equal to 18 years old";

    public final static String MSG_ERR_NEGATIVE = "[ERROR] This value cannot be negative";
    public final static String MSG_ERR_HOURS = "[ERROR] Employee hours cannot be negative or greater than 40";
    public final static String MSG_ERR_WORKLOAD = "[ERROR] Employee's workload cannot be greather than 100%";
    public final static String MSG_ERR_TASK_NOT_ALLOWED = "[ERROR] Employee's task are not allowed";
    public final static String MSG_ERR_COMMISSION_RATE = "[ERROR] Employee's commission rate must be in range [1,10]";

    public EmployeeException() {
        super();
    }

    public EmployeeException(String msg) {
        super(msg);
    }

}
