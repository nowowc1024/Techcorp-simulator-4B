public class Main {

    public static void main(String[] args) {

        // =============================================
        // Module A & B: Variables, objects, constructor
        // =============================================

        System.out.println("=== TechCorp Simulator Starting ===");
        System.out.println();

        // Create the company
        Company techCorp = new Company("TechCorp");

        // Create three employees using constructors (B3)
        // Stored as general Employee type - polymorphism (D6)
        Employee anna  = new Developer("Anna",  9, 8000);
        Employee piotr = new Tester("Piotr",    6, 6500);
        Employee kate  = new Manager("Kate",    7, 9000);

        // Hire employees into the company
        techCorp.hire(anna);
        techCorp.hire(piotr);
        techCorp.hire(kate);

        // Print each employee's info (C1 - getters)
        System.out.println("=== Hired Employees ===");
        anna.printInfo();
        piotr.printInfo();
        kate.printInfo();
        System.out.println();

        // =============================================
        // Module C: Project and simulation (C2, C3, C4)
        // =============================================

        // First simulation - requiredWork = 50
        System.out.println("=== Simulation 1: Required Work = 50 ===");
        Project project1 = new Project("TechCorp App v1", 50);
        techCorp.startProject(project1);

        project1.addEmployee(anna);
        project1.addEmployee(piotr);
        project1.addEmployee(kate);

        // Run simulation loop (C5)
        while (!project1.isFinished()) {
            project1.workOneTurn();
            project1.printStatus();
        }

        System.out.println("Project 1 completed in " + project1.getTurns() + " turn(s)!");
        System.out.println();

        // =============================================
        // C5: Change required work and observe results
        // =============================================

        // Second simulation - requiredWork = 100 (observe different result)
        System.out.println("=== Simulation 2: Required Work = 100 ===");
        Project project2 = new Project("TechCorp App v2", 100);
        techCorp.startProject(project2);

        project2.addEmployee(anna);
        project2.addEmployee(piotr);
        project2.addEmployee(kate);

        while (!project2.isFinished()) {
            project2.workOneTurn();
            project2.printStatus();
        }

        System.out.println("Project 2 completed in " + project2.getTurns() + " turn(s)!");
        System.out.println();

        // =============================================
        // Full company status printout
        // =============================================

        techCorp.printStatus();

        // =============================================
        // D6: Polymorphism demonstration
        // =============================================

        System.out.println("=== Polymorphism Demo ===");
        Employee[] allEmployees = { anna, piotr, kate };
        for (Employee e : allEmployees) {
            System.out.println(e.getName() + " produced work: " + e.work());
        }

        // =============================================
        // C1: Skill validation demonstration
        // =============================================

        System.out.println();
        System.out.println("=== Skill Validation Demo ===");
        try {
            Employee invalid = new Developer("BadHire", 15, 5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Simulation Complete ===");
    }
}