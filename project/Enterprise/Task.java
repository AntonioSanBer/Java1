package Enterprise;

public class Task {
    private String name;
    private int workload;
    private TaskType type;

    public Task(String name, int workload, TaskType type) throws IllegalArgumentException{
        String regex = "T[0-9]+:.*";
        if(!name.matches(regex)) {
            throw new IllegalArgumentException("[ERROR] Task's parameter is incorrect!!");
        }
        this.setWorkload(workload);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        if(workload < 0 || workload > 100) {
            throw new IllegalArgumentException("[ERROR] Task's parameter is incorrect!!");
        }
        this.workload = workload;
    }

    public TaskType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "("+getType()+") " + getName()+ " - workload " + workload + "%";
    }
}