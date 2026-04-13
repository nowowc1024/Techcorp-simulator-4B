package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String         name;
    private double         cash;
    private List<Employee> employees;
    private List<Project>  projects;

    public Company(String name, double cash) {
        this.name      = name;
        this.cash      = cash;
        this.employees = new ArrayList<>();
        this.projects  = new ArrayList<>();
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void startProject(Project project) {
        projects.add(project);
        project.start();
    }

    public void paySalaries() {
        for (Employee e : employees) {
            cash -= e.getSalary();
        }
    }

    public String         getName()      { return name; }
    public double         getCash()      { return cash; }
    public List<Employee> getEmployees() { return employees; }
    public List<Project>  getProjects()  { return projects; }
}
