package com.example.techcorp.engine;

import com.example.techcorp.domain.Company;
import com.example.techcorp.domain.Project;
import com.example.techcorp.domain.ProjectStatus;
import com.example.techcorp.ui.ConsoleUI;

public class GameEngine {

    private Company company;
    private ConsoleUI ui;
    private boolean running;
    private int turn;

    private static final int MAX_TURNS = 10;

    public GameEngine(Company company, ConsoleUI ui) {
        this.company = company;
        this.ui = ui;
        this.running = true;
        this.turn = 1;
    }

    public void start() {
        while (running) {
            ui.showTurnHeader(turn);
            ui.showCompanyStatus(company);
            ui.showMainMenu();

            int choice = ui.readMenuChoice();

            handleChoice(choice);

            if (running) {
                advanceTurn();
                turn++;
            }
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> ui.showCompanyStatus(company);
            case 2 -> startPlannedProjects();
            case 3 -> workOnProjects();
            case 4 -> showActiveProjects();
            case 5 -> pauseProject();
            case 6 -> { running = false; ui.showMessage("Goodbye!"); }
            default -> ui.showMessage("Please enter a number from 1 to 6.");
        }
    }

    private void showActiveProjects() {
        ui.showMessage("--- Active Projects ---");
        boolean found = false;
        for (Project p : company.getProjects()) {
            if (!p.isFinished()) {
                ui.showMessage("  " + p.getName()
                    + " | " + p.getStatus()
                    + " | " + p.getProgress() + "/" + p.getRequiredWork());
                found = true;
            }
        }
        if (!found) ui.showMessage("  (none — all projects are finished)");
    }

    private void pauseProject() {
        ui.showMessage("Enter the project name to pause:");
        String name = ui.readLine();
        for (Project p : company.getProjects()) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.pause();
                ui.showMessage("'" + name + "' is now ON HOLD.");
                return;
            }
        }
        ui.showMessage("No project found with that name.");
    }

    private void startPlannedProjects() {
        for (Project p : company.getProjects()) {
            if (p.getStatus() == ProjectStatus.PLANNED) {
                p.start();
            }
        }
        ui.showMessage("All planned projects started.");
    }

    private void workOnProjects() {
        for (Project p : company.getProjects()) {
            p.workOneTurn();
        }
        ui.showMessage("Projects worked for one turn.");
    }

    private void advanceTurn() {
        if (allProjectsFinished()) {
            ui.showMessage("All projects complete — YOU WIN!");
            running = false;
            return;
        }
        if (company.getCash() < 0) {
            ui.showMessage("Cash is negative — GAME OVER.");
            running = false;
            return;
        }
        if (turn >= MAX_TURNS) {
            ui.showMessage("Turn limit reached — GAME OVER.");
            running = false;
        }
    }

    private boolean allProjectsFinished() {
        if (company.getProjects().isEmpty()) return false;
        for (Project p : company.getProjects()) {
            if (!p.isFinished()) return false;
        }
        return true;
    }
}