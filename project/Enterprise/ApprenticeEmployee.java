package Enterprise;

public class ApprenticeEmployee extends Employee {

    public ApprenticeEmployee() throws EmployeeException {
        super();
    }

    public ApprenticeEmployee(String name, String street, String email, int birthYear) throws EmployeeException {
        super(name, street, email, birthYear, EducationLevel.STUDENT);
    }

    //from Employee abstract class
    @Override
    public void todoTask(Task task) throws EmployeeException {
        if (task.getType() == TaskType.ISSUE) {
            addWorkload(task.getWorkload());
        } else {
            throw new EmployeeException(EmployeeException.MSG_ERR_TASK_NOT_ALLOWED);
        }
    }

    //from Employee abstract class
    @Override
    public String presentation() {
        return "I'm an apprentice";
    }

    @Override
    public String toString() {
        return "Apprentice Employee - " + super.toString();
    }

}

