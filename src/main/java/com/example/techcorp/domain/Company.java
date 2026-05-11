package com.example.techcorp.domain;

import com.example.techcorp.util.InputValidator;
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
        InputValidator.requireNonBlank(name, "Company name");
        InputValidator.requireNonNegative(initialCash, "Initial cash");
        this.name      = name;
        this.budget    = new Budget(initialCash);
        this.employees = new ArrayList<>();
        this.projects  = new ArrayList<>();
    }

    public void hire(Employee employee) {
        InputValidator.requireNotNull(employee, "Employee");
        if (employees.contains(employee))
            throw new IllegalStateException(
                employee.getName() + " is already employed here.");
        employees.add(employee);
    }

    public void addProject(Project project) {
        InputValidator.requireNotNull(project, "Project");
        if (projects.contains(project))
            throw new IllegalStateException("Project already registered.");
        projects.add(project);
    }

    public void startProject(Project project) {
        addProject(project);
        project.start();
    }

    public double paySalaries() {
        double total = employees.stream()
                                .mapToDouble(Employee::getSalary)
                                .sum();
        budget.debit(total);
        return total;
    }

    public void receiveFunds(double amount) {
        InputValidator.requirePositive(amount, "Amount");
        budget.credit(amount);
    }

    public String getName()   { return name; }
    public double getCash()   { return budget.getBalance(); }
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
