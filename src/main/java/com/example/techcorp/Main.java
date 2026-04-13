package com.example.techcorp;

import com.example.techcorp.domain.Company;
import com.example.techcorp.domain.Developer;
import com.example.techcorp.domain.Manager;
import com.example.techcorp.domain.Project;
import com.example.techcorp.domain.Tester;
import com.example.techcorp.engine.GameEngine;
import com.example.techcorp.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        // 1. Build the company
        Company company = new Company("TechCorp", 50_000);

        // 2. Hire employees
        company.hire(new Developer("Anna",  9, 8_000));
        company.hire(new Tester   ("Piotr", 6, 6_500));
        company.hire(new Manager  ("Ewa",   7, 9_000));

        // 3. Create projects
        Project mobileApp = new Project("Mobile App", 30);
        Project website   = new Project("Website",    20);

        // 4. Assign all employees to the mobile app
        for (var employee : company.getEmployees()) {
            mobileApp.addEmployee(employee);
        }

        // 5. Register projects with the company
        company.startProject(mobileApp);
        company.startProject(website);

        // 6. Start the game
        ConsoleUI  ui     = new ConsoleUI();
        GameEngine engine = new GameEngine(company, ui);
        engine.start();
    }
}