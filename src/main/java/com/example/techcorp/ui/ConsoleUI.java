package com.example.techcorp.ui;

import com.example.techcorp.domain.Company;
import com.example.techcorp.domain.Employee;
import com.example.techcorp.domain.Project;
import com.example.techcorp.domain.ProjectStatus;
import com.example.techcorp.engine.Score;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all console input and output.
 * Keeps display logic completely separate from game logic (GameEngine)
 * and domain logic (Company, Project, Employee).
 */
public class ConsoleUI {

    private static final String DIVIDER =
        "══════════════════════════════════════════════════════";
    private static final String THIN =
        "──────────────────────────────────────────────────────";

    private final Scanner scanner = new Scanner(System.in);

    // ── Headers ──────────────────────────────────────────────────────────────

    public void showWelcome(String companyName) {
        System.out.println(DIVIDER);
        System.out.println("  Welcome to TechCorp Simulator");
        System.out.println("  Managing: " + companyName);
        System.out.println(DIVIDER);
        System.out.println("  Goal: complete all projects before turn 10.");
        System.out.println("  Pay salaries each turn — don't go bankrupt!");
        System.out.println(DIVIDER);
    }

    public void showTurnHeader(int turn, int maxTurns) {
        System.out.println();
        System.out.println(DIVIDER);
        System.out.printf("  TURN %d of %d%n", turn, maxTurns);
        System.out.println(DIVIDER);
    }

    // ── Company status ───────────────────────────────────────────────────────

    public void showCompanyStatus(Company company, Score score) {
        System.out.println(THIN);
        System.out.printf("  Company : %s%n", company.getName());
        System.out.printf("  Cash    : %,.2f PLN%n", company.getCash());
        System.out.printf("  Payroll : %,.2f PLN / turn%n", company.getTotalSalaries());
        System.out.printf("  %s%n", score);
        System.out.println(THIN);

        System.out.println("  EMPLOYEES:");
        List<Employee> employees = company.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("    (no employees)");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                Employee e = employees.get(i);
                System.out.printf("    [%d] %s  →  work/turn: %d%n",
                    i + 1, e, e.work());
            }
        }
        System.out.println(THIN);

        System.out.println("  PROJECTS:");
        List<Project> projects = company.getProjects();
        if (projects.isEmpty()) {
            System.out.println("    (no projects)");
        } else {
            for (int i = 0; i < projects.size(); i++) {
                Project p = projects.get(i);
                System.out.printf("    [%d] %s%n", i + 1, p);
                if (!p.getEmployees().isEmpty()) {
                    System.out.print("        Assigned: ");
                    p.getEmployees().forEach(e ->
                        System.out.print(e.getName() + " "));
                    System.out.println();
                }
            }
        }
        System.out.println(THIN);
    }

    // ── Main menu ────────────────────────────────────────────────────────────

    public void showMainMenu() {
        System.out.println();
        System.out.println("  ACTIONS:");
        System.out.println("  1. Show full company status");
        System.out.println("  2. Assign employee to a project");
        System.out.println("  3. Unassign employee from a project");
        System.out.println("  4. Start a PLANNED project");
        System.out.println("  5. Pause an IN_PROGRESS project");
        System.out.println("  6. Resume an ON_HOLD project");
        System.out.println("  7. Cancel a project");
        System.out.println("  8. End turn  (work + pay salaries)");
        System.out.println("  9. Exit game");
        System.out.println();
    }

    public int readMenuChoice() {
        while (true) {
            System.out.print("  Enter choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                scanner.nextLine();
                System.out.println("  Please enter a number.");
            }
        }
    }

    // ── Selection helpers ────────────────────────────────────────────────────

    public Employee selectEmployee(Company company) {
        List<Employee> list = company.getEmployees();
        if (list.isEmpty()) {
            showMessage("No employees available.");
            return null;
        }
        System.out.println("  Select employee (0 to cancel):");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("    [%d] %s%n", i + 1, list.get(i));
        }
        int idx = readIndexChoice(list.size());
        return idx == 0 ? null : list.get(idx - 1);
    }

    public Project selectProject(Company company, ProjectStatus statusFilter) {
        List<Project> all = company.getProjects();
        List<Project> filtered = all.stream()
            .filter(p -> statusFilter == null || p.getStatus() == statusFilter)
            .toList();

        if (filtered.isEmpty()) {
            String label = statusFilter == null ? "" : statusFilter + " ";
            showMessage("No " + label + "projects available.");
            return null;
        }
        System.out.println("  Select project (0 to cancel):");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.printf("    [%d] %s%n", i + 1, filtered.get(i));
        }
        int idx = readIndexChoice(filtered.size());
        return idx == 0 ? null : filtered.get(idx - 1);
    }

    private int readIndexChoice(int max) {
        while (true) {
            System.out.print("  Enter number (0 to cancel): ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                scanner.nextLine();
                if (n >= 0 && n <= max) return n;
            } else {
                scanner.nextLine();
            }
            System.out.printf("  Please enter a number between 0 and %d.%n", max);
        }
    }

    // ── End-of-game ──────────────────────────────────────────────────────────

    public void showVictory(Score score) {
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("  ALL PROJECTS COMPLETE — YOU WIN!");
        System.out.printf("  Final %s%n", score);
        System.out.println(DIVIDER);
    }

    public void showGameOver(String reason, Score score) {
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("  GAME OVER — " + reason);
        System.out.printf("  Final %s%n", score);
        System.out.println(DIVIDER);
    }

    public void showTurnSummary(double salariesPaid, Score score) {
        System.out.println();
        System.out.printf("  Salaries paid: %,.2f PLN%n", salariesPaid);
        System.out.printf("  %s%n", score);
    }

    // ── Generic ──────────────────────────────────────────────────────────────

    public void showMessage(String message) {
        System.out.println("  " + message);
    }

    public String readLine() {
        System.out.print("  > ");
        return scanner.nextLine().trim();
    }
}