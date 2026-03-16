import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;
    private int turns;
    private List<Employee> team;

    public Project(String name, int requiredWork) {
        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
        this.turns = 0;
        this.team = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        team.add(e);
    }

    public void workOneTurn() {
        for (Employee e : team) {
            progress += e.work();
        }
        turns++;
    }

    public boolean isFinished() {
        return progress >= requiredWork;
    }

    public int getTurns() {
        return turns;
    }

    public void printStatus() {
        System.out.println("Project: " + name);
        System.out.println("Progress: " + progress + " / " + requiredWork);
        System.out.println("Turns so far: " + turns);
        System.out.println("Finished: " + isFinished());
        System.out.println("-----------------------------");
    }
}