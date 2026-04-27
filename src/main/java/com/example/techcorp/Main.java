package com.example.techcorp;

import com.example.techcorp.domain.*;
import com.example.techcorp.engine.GameEngine;
import com.example.techcorp.ui.ConsoleUI;

/**
 * Entry point for TechCorp Simulator.
 *
 * Responsibilities here are minimal:
 *   1. Create the company and hire its initial team.
 *   2. Register the starting projects.
 *   3. Hand control to GameEngine.
 *
 * All game logic lives in GameEngine.
 * All display logic lives in ConsoleUI.
 * All domain rules live in the domain package.
 */
public class Main {

    public static void main(String[] args) {

        // ── Build the company ────────────────────────────────────────────────
        Company company = new Company("TechCorp", 50_000);

        // Developer: work output = skill × 2
        // Tester:    work output = skill × 1
        // Manager:   work output = skill × 3
        company.hire(new Developer("Anna",  9, 8_000));
        company.hire(new Tester   ("Piotr", 6, 6_500));
        company.hire(new Manager  ("Ewa",   7, 9_000));

        // Register projects as PLANNED — player assigns workers and starts them
        company.addProject(new Project("Mobile App",    30));
        company.addProject(new Project("Website",       20));
        company.addProject(new Project("Data Service",  15));

        // ── Start the game ───────────────────────────────────────────────────
        new GameEngine(company, new ConsoleUI()).start();
    }
}