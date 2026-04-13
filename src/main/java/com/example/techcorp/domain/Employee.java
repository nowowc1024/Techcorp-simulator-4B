package com.example.techcorp.domain;

public class Employee implements Workable {
    private String name;
    private int skill;
    private double salary;

    public Employee(String name, int skill, double salary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        if (skill <= 0) {
            throw new IllegalArgumentException("Skill must be greater than zero.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.name = name;
        this.skill = skill;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getSkill() { return skill; }
    public double getSalary() { return salary; }

    @Override
    public int work() {
        return skill;
    }

    public int getProductivity() { return skill; }
    public void printInfo() {
        System.out.println("Employee: " + name + ", Skill: " + skill + ", Salary: " + salary);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', skill=" + skill + ", salary=" + salary + "}";
    }
}
