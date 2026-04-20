package com.example.techcorp;

import com.example.techcorp.domain.*;
import com.example.techcorp.engine.GameEngine;
import com.example.techcorp.ui.ConsoleUI;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // === LESSON 8 DEMO ===================================
        List<Employee> team = new ArrayList<>();
        team.add(new Developer("Anna",  9, 8_000));
        team.add(new Tester   ("Piotr", 6, 6_500));
        team.add(new Manager  ("Ewa",   7, 9_000));
        System.out.println("=== Lesson 8: Polymorphism Demo ===");
        int totalWork = 0;
        for (Employee e : team) {
            int output = e.work();
            totalWork += output;
            System.out.println(e.getClass().getSimpleName()
                + " | Role: " + e.getRole() + " | Work: " + output);
        }
        System.out.println("Total work output: " + totalWork);
        System.out.println("====================================");

        // === LESSON 9 DEMO ===================================
        System.out.println("=== Lesson 9: Encapsulation Demo ===");
        try {
            new Employee("Ghost", 5, 500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ex1: " + e.getMessage());
        }
        Project tiny = new Project("Tiny", 1);
        tiny.start();
        tiny.addEmployee(new Developer("Anna", 9, 8_000));
        tiny.workOneTurn();
        try {
            tiny.addEmployee(new Tester("Piotr", 6, 6_500));
        } catch (IllegalStateException e) {
            System.out.println("Ex2: " + e.getMessage());
        }
        Budget budget = new Budget(1_000.0);
        budget.debit(400.0);
        System.out.println("Ex3 balance: " + budget.getBalance());
        try {
            budget.debit(800.0);
        } catch (IllegalStateException e) {
            System.out.println("Ex3: " + e.getMessage());
        }
        Company demo = new Company("Demo Corp", 20_000);
        Employee anna = new Developer("Anna", 9, 8_000);
        demo.hire(anna);
        try {
            demo.hire(anna);
        } catch (IllegalStateException e) {
            System.out.println("Ex4: " + e.getMessage());
        }
        System.out.println("====================================");

        // === GAME SETUP ======================================
        Company company = new Company("TechCorp", 50_000);
        company.hire(new Developer("Anna",  9, 8_000));
        company.hire(new Tester   ("Piotr", 6, 6_500));
        company.hire(new Manager  ("Ewa",   7, 9_000));
        Project mobileApp = new Project("Mobile App", 30);
        Project website   = new Project("Website",    20);
        for (var emp : company.getEmployees()) mobileApp.addEmployee(emp);
        company.startProject(mobileApp);
        company.startProject(website);
        new GameEngine(company, new ConsoleUI()).start();
    }
}
