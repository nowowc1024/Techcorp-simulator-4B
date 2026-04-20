package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private String         name;
    private double         cash;
    private List<Employee> employees;
    private List<Project>  projects;

    public Company(String name, double cash) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Company name cannot be blank.");
        if (cash < 0)
            throw new IllegalArgumentException("Initial cash cannot be negative.");
        this.name      = name;
        this.cash      = cash;
        this.employees = new ArrayList<>();
        this.projects  = new ArrayList<>();
    }

    public void hire(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("Cannot hire a null employee.");
        if (employees.contains(employee))
            throw new IllegalStateException("Employee already hired.");
        employees.add(employee);
    }

    public void startProject(Project project) {
        if (project == null)
            throw new IllegalArgumentException("Cannot start a null project.");
        if (projects.contains(project))
            throw new IllegalStateException("Project already registered.");
        projects.add(project);
        project.start();
    }

    public void paySalaries() {
        double total = 0;
        for (Employee e : employees) total += e.getSalary();
        if (total > cash)
            throw new IllegalStateException("Insufficient cash to pay salaries.");
        cash -= total;
    }

    public String getName() { return name; }
    public double getCash() { return cash; }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }
}
