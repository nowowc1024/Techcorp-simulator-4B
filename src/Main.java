public class Main {

    public static void main(String[] args) {

        // Create company
        Company techCorp = new Company("TechCorp");

        // Hire employees
        Employee anna  = new Developer("Anna",  9, 8000);
        Employee piotr = new Tester("Piotr",    6, 6500);
        Employee kate  = new Manager("Kate",    7, 9000);

        techCorp.hire(anna);
        techCorp.hire(piotr);
        techCorp.hire(kate);

        // Create and start a project
        Project project = new Project("TechCorp App", 50);
        techCorp.startProject(project);

        // Assign team
        project.addEmployee(anna);
        project.addEmployee(piotr);
        project.addEmployee(kate);

        // Run simulation loop
        int turns = 0;
        while (!project.isFinished()) {
            project.workOneTurn();
            turns++;
            System.out.println("--- Turn " + turns + " ---");
            project.printStatus();
        }

        System.out.println("Project completed in " + turns + " turn(s)!");
        techCorp.printStatus();
    }
}