import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;
    private List<Employee> team;

    public Project(String name, int requiredWork) {
        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
        this.team = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        team.add(e);
    }

    public void workOneTurn() {
        for (Employee e : team) {
            progress += e.work();
        }
    }

    public boolean isFinished() {
        return progress >= requiredWork;
    }

    public void printStatus() {
        System.out.println("Project: " + name);
        System.out.println("Progress: " + progress + " / " + requiredWork);
        System.out.println("Finished: " + isFinished());
    }
}