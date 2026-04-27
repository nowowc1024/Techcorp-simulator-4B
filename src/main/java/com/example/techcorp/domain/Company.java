package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central domain class representing the player's company.
 * Owns the Budget, the employee roster, and the project portfolio.
 */
public class Company {

    private final String         name;
    private final Budget         budget;
    private final List<Employee> employees;
    private final List<Project>  projects;

    public Company(String name, double initialCash) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Company name cannot be blank.");
        if (initialCash < 0)
            throw new IllegalArgumentException("Initial cash cannot be negative.");
        this.name      = name;
        this.budget    = new Budget(initialCash);
        this.employees = new ArrayList<>();
        this.projects  = new ArrayList<>();
    }

    // ── Employees ────────────────────────────────────────────────────────────

    public void hire(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("Cannot hire a null employee.");
        if (employees.contains(employee))
            throw new IllegalStateException(
                employee.getName() + " is already employed here.");
        employees.add(employee);
    }

    // ── Projects ─────────────────────────────────────────────────────────────

    /**
     * Registers a project as PLANNED. Player starts it via the menu.
     */
    public void addProject(Project project) {
        if (project == null)
            throw new IllegalArgumentException("Cannot add a null project.");
        if (projects.contains(project))
            throw new IllegalStateException("Project already registered.");
        projects.add(project);
    }

    /**
     * Registers AND immediately starts a project.
     */
    public void startProject(Project project) {
        addProject(project);
        project.start();
    }

    // ── Finances ─────────────────────────────────────────────────────────────

    /**
     * Deducts total salaries from the budget.
     * Called by GameEngine at the end of each turn.
     *
     * @return total amount paid
     * @throws IllegalStateException if funds are insufficient
     */
    public double paySalaries() {
        double total = employees.stream()
                                .mapToDouble(Employee::getSalary)
                                .sum();
        budget.debit(total);
        return total;
    }

    public void receiveFunds(double amount) {
        budget.credit(amount);
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getName()  { return name; }
    public double getCash()  { return budget.getBalance(); }
    public Budget getBudget() { return budget; }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }

    public double getTotalSalaries() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}