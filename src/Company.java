import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee e) {
        employees.add(e);
    }

    public void startProject(Project p) {
        projects.add(p);
    }

    public void printStatus() {
        System.out.println("=============================");
        System.out.println("Company: " + name);
        System.out.println("=============================");
        System.out.println("Employees (" + employees.size() + "):");
        for (Employee e : employees) {
            e.printInfo();
        }
        System.out.println("-----------------------------");
        System.out.println("Projects (" + projects.size() + "):");
        for (Project p : projects) {
            p.printStatus();
        }
    }
}