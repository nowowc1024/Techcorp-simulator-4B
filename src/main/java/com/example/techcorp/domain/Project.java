package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String        name;
    private int           requiredWork;
    private int           progress;
    private ProjectStatus status;
    private List<Employee> employees;

    public Project(String name, int requiredWork) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Project name cannot be blank.");
        if (requiredWork <= 0)
            throw new IllegalArgumentException("Required work must be positive.");
        this.name         = name;
        this.requiredWork = requiredWork;
        this.progress     = 0;
        this.status       = ProjectStatus.PLANNED;
        this.employees    = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (isFinished())
            throw new IllegalStateException("Cannot add employee to finished project.");
        if (employee == null)
            throw new IllegalArgumentException("Employee cannot be null.");
        employees.add(employee);
    }

    public void workOneTurn() {
        if (status != ProjectStatus.IN_PROGRESS) return;
        if (isFinished()) return;
        for (Employee e : employees) { progress += e.getProductivity(); }
        if (progress >= requiredWork) {
            progress = requiredWork;
            status   = ProjectStatus.FINISHED;
        }
    }

    public void start() {
        if (status == ProjectStatus.PLANNED) status = ProjectStatus.IN_PROGRESS;
    }

    public void pause() {
        if (status == ProjectStatus.IN_PROGRESS) status = ProjectStatus.ON_HOLD;
    }

    public String        getName()         { return name; }
    public ProjectStatus getStatus()       { return status; }
    public int           getProgress()     { return progress; }
    public int           getRequiredWork() { return requiredWork; }
    public boolean       isFinished()      { return status == ProjectStatus.FINISHED; }
    public List<Employee> getEmployees()   { return employees; }
}
