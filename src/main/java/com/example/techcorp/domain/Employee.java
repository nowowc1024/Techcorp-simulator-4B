package com.example.techcorp.domain;

import com.example.techcorp.util.InputValidator;

/**
 * Abstract base class for all employee types.
 * Enforces validation on construction and implements the Workable interface.
 */
public class Employee implements Workable {

    private static final double MINIMUM_SALARY = 1_000.0;

    private String name;
    private int    skill;
    private double salary;

    public Employee(String name, int skill, double salary) {
        setName(name);
        setSkill(skill);
        setSalary(salary);
    }

    public void setName(String name) {
        InputValidator.requireNonBlank(name, "Employee name");
        this.name = name;
    }

    public void setSkill(int skill) {
        InputValidator.requirePositive(skill, "Skill");
        this.skill = skill;
    }

    public void setSalary(double salary) {
        InputValidator.requireNonNegative(salary, "Salary");
        InputValidator.requireMinimumSalary(salary, MINIMUM_SALARY);
        this.salary = salary;
    }

    public String getName()   { return name; }
    public int    getSkill()  { return skill; }
    public double getSalary() { return salary; }

    public String getRole() { return "Employee"; }

    @Override
    public int work() { return skill; }

    @Override
    public String toString() {
        return String.format("%-10s | Role: %-10s | Skill: %2d | Salary: %,.0f",
            name, getRole(), skill, salary);
    }
}
