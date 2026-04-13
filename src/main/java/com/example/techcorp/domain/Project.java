package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String         name;
    private int            requiredWork;
    private int            progress;
    private ProjectStatus  status;
    private List<Employee> employees;

    public Project(String name, int requiredWork) {
        this.name         = name;
        this.requiredWork = requiredWork;
        this.progress     = 0;
        this.status       = ProjectStatus.PLANNED;
        this.employees    = new ArrayList<>();
    }

    public void addEmployee(Employee employee) { employees.add(employee); }

    public void start() {
        if (status == ProjectStatus.PLANNED) status = ProjectStatus.IN_PROGRESS;
    }

    public void pause() {
        if (status == ProjectStatus.IN_PROGRESS) status = ProjectStatus.ON_HOLD;
    }

    public void workOneTurn() {
        if (status != ProjectStatus.IN_PROGRESS) return;
        for (Employee e : employees) { progress += e.getProductivity(); }
        if (progress >= requiredWork) { progress = requiredWork; status = ProjectStatus.FINISHED; }
    }

    public String         getName()         { return name; }
    public ProjectStatus  getStatus()       { return status; }
    public int            getProgress()     { return progress; }
    public int            getRequiredWork() { return requiredWork; }
    public boolean        isFinished()      { return status == ProjectStatus.FINISHED; }
    public List<Employee> getEmployees()    { return employees; }
}
