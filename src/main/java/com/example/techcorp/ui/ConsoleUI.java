package com.example.techcorp.ui;

import com.example.techcorp.domain.Company;
import com.example.techcorp.domain.Project;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    public void showTurnHeader(int turn) {
        System.out.println();
        System.out.println("=== TURN " + turn + " ===");
    }

    public void showMainMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Show company status");
        System.out.println("2. Start planned projects");
        System.out.println("3. Work on projects");
        System.out.println("4. Show active (unfinished) projects");
        System.out.println("5. Pause a project");
        System.out.println("6. Exit game");
    }

    public int readMenuChoice() {
        while (true) {
            System.out.print("Enter choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                scanner.nextLine();
                System.out.println("That is not a number. Please try again.");
            }
        }
    }

    public String readLine() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public void showCompanyStatus(Company company) {
        System.out.println("Company : " + company.getName());
        System.out.println("Cash    : " + company.getCash());
        System.out.println("Projects:");
        for (Project p : company.getProjects()) {
            System.out.println("  - " + p.getName()
                + " | " + p.getStatus()
                + " | " + p.getProgress() + "/" + p.getRequiredWork());
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}