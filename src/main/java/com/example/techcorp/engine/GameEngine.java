package com.example.techcorp.engine;

import com.example.techcorp.domain.*;
import com.example.techcorp.ui.ConsoleUI;

/**
 * Controls the main game loop.
 *
 * Responsibilities:
 *   - Present each turn's menu and dispatch player choices
 *   - Advance the simulation (work + salary payment) at end of turn
 *   - Track win / lose conditions
 *   - Maintain the Score
 *
 * Does NOT contain display code (→ ConsoleUI) or domain rules (→ domain package).
 */
public class GameEngine {

    private static final int MAX_TURNS = 10;

    private final Company   company;
    private final ConsoleUI ui;
    private final Score     score;

    private boolean running;
    private int     turn;

    public GameEngine(Company company, ConsoleUI ui) {
        if (company == null) throw new IllegalArgumentException("Company cannot be null.");
        if (ui      == null) throw new IllegalArgumentException("ConsoleUI cannot be null.");
        this.company = company;
        this.ui      = ui;
        this.score   = new Score();
        this.running = true;
        this.turn    = 1;
    }

    // ── Main loop ────────────────────────────────────────────────────────────

    public void start() {
        ui.showWelcome(company.getName());

        while (running) {
            ui.showTurnHeader(turn, MAX_TURNS);
            ui.showCompanyStatus(company, score);
            ui.showMainMenu();

            int choice = ui.readMenuChoice();
            handleChoice(choice);
        }
    }

    // ── Action dispatcher ────────────────────────────────────────────────────

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> ui.showCompanyStatus(company, score);
            case 2 -> assignEmployee();
            case 3 -> unassignEmployee();
            case 4 -> startProject();
            case 5 -> pauseProject();
            case 6 -> resumeProject();
            case 7 -> cancelProject();
            case 8 -> endTurn();
            case 9 -> confirmExit();
            default -> ui.showMessage("Please enter a number from 1 to 9.");
        }
    }

    // ── Player actions ───────────────────────────────────────────────────────

    private void assignEmployee() {
        Employee emp = ui.selectEmployee(company);
        if (emp == null) return;

        Project project = ui.selectProject(company, null);
        if (project == null) return;

        try {
            project.addEmployee(emp);
            ui.showMessage(emp.getName() + " assigned to '" + project.getName() + "'.");
        } catch (IllegalStateException e) {
            ui.showMessage("Cannot assign: " + e.getMessage());
        }
    }

    private void unassignEmployee() {
        Project project = ui.selectProject(company, null);
        if (project == null) return;

        if (project.getEmployees().isEmpty()) {
            ui.showMessage("No employees assigned to '" + project.getName() + "'.");
            return;
        }

        ui.showMessage("Select employee to remove from '" + project.getName() + "':");
        var assigned = project.getEmployees();
        for (int i = 0; i < assigned.size(); i++) {
            ui.showMessage("  [" + (i + 1) + "] " + assigned.get(i).getName());
        }
        ui.showMessage("  (0 to cancel)");

        while (true) {
            String line = ui.readLine();
            try {
                int idx = Integer.parseInt(line);
                if (idx == 0) return;
                if (idx >= 1 && idx <= assigned.size()) {
                    Employee emp = assigned.get(idx - 1);
                    project.removeEmployee(emp);
                    ui.showMessage(emp.getName() + " removed from '"
                        + project.getName() + "'.");
                    return;
                }
            } catch (NumberFormatException ignored) { }
            ui.showMessage("Please enter a valid number.");
        }
    }

    private void startProject() {
        Project p = ui.selectProject(company, ProjectStatus.PLANNED);
        if (p == null) return;
        try {
            p.start();
            ui.showMessage("'" + p.getName() + "' is now IN_PROGRESS.");
        } catch (IllegalStateException e) {
            ui.showMessage("Cannot start: " + e.getMessage());
        }
    }

    private void pauseProject() {
        Project p = ui.selectProject(company, ProjectStatus.IN_PROGRESS);
        if (p == null) return;
        try {
            p.pause();
            ui.showMessage("'" + p.getName() + "' is now ON_HOLD.");
        } catch (IllegalStateException e) {
            ui.showMessage("Cannot pause: " + e.getMessage());
        }
    }

    private void resumeProject() {
        Project p = ui.selectProject(company, ProjectStatus.ON_HOLD);
        if (p == null) return;
        try {
            p.resume();
            ui.showMessage("'" + p.getName() + "' is now IN_PROGRESS again.");
        } catch (IllegalStateException e) {
            ui.showMessage("Cannot resume: " + e.getMessage());
        }
    }

    private void cancelProject() {
        Project p = ui.selectProject(company, null);
        if (p == null) return;
        try {
            p.cancel();
            score.projectCancelled();
            ui.showMessage("'" + p.getName() + "' has been CANCELLED. (-50 pts)");
        } catch (IllegalStateException e) {
            ui.showMessage("Cannot cancel: " + e.getMessage());
        }
    }

    private void endTurn() {
        // 1. All IN_PROGRESS projects do work
        for (Project p : company.getProjects()) {
            boolean wasDone = p.isFinished();
            p.workOneTurn();
            if (!wasDone && p.isFinished()) {
                score.projectCompleted();
                ui.showMessage("🏆 '" + p.getName() + "' COMPLETED! (+200 pts)");
            }
        }

        // 2. Deduct turn penalty
        score.turnTaken();

        // 3. Pay salaries
        double paid;
        try {
            paid = company.paySalaries();
        } catch (IllegalStateException e) {
            ui.showMessage("BANKRUPT — cannot pay salaries! " + e.getMessage());
            ui.showGameOver("Company went bankrupt.", score);
            running = false;
            return;
        }

        ui.showTurnSummary(paid, score);

        // 4. Check win condition
        if (allProjectsResolved()) {
            if (allProjectsFinished()) {
                score.earlyFinishBonus();
                ui.showVictory(score);
            } else {
                ui.showGameOver(
                    "All projects cancelled — nothing delivered.", score);
            }
            running = false;
            return;
        }

        // 5. Check turn limit
        if (turn >= MAX_TURNS) {
            ui.showGameOver("Turn limit reached.", score);
            running = false;
            return;
        }

        turn++;
    }

    private void confirmExit() {
        ui.showMessage("Are you sure you want to quit? (yes / no)");
        String answer = ui.readLine();
        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
            ui.showMessage("Thanks for playing. Goodbye!");
            running = false;
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private boolean allProjectsResolved() {
        if (company.getProjects().isEmpty()) return false;
        for (Project p : company.getProjects()) {
            if (!p.isFinished() && !p.isCancelled()) return false;
        }
        return true;
    }

    private boolean allProjectsFinished() {
        for (Project p : company.getProjects()) {
            if (!p.isFinished()) return false;
        }
        return true;
    }
}