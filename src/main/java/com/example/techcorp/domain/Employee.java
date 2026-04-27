package com.example.techcorp.domain;

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

    // ── Setters with validation ──────────────────────────────────────────────

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Employee name cannot be null or blank.");
        this.name = name;
    }

    public void setSkill(int skill) {
        if (skill <= 0)
            throw new IllegalArgumentException("Skill must be greater than zero.");
        this.skill = skill;
    }

    public void setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative.");
        if (salary < MINIMUM_SALARY)
            throw new IllegalArgumentException(
                "Salary cannot be below minimum: " + MINIMUM_SALARY);
        this.salary = salary;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getName()   { return name; }
    public int    getSkill()  { return skill; }
    public double getSalary() { return salary; }

    /**
     * Returns a label describing this employee's role.
     * Subclasses override to return their specific role name.
     */
    public String getRole() { return "Employee"; }

    // ── Workable ─────────────────────────────────────────────────────────────

    /**
     * Base work output equals the employee's skill level.
     * Subclasses override to apply role-specific multipliers.
     */
    @Override
    public int work() { return skill; }

    // ── Utility ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("%-10s | Role: %-10s | Skill: %2d | Salary: %,.0f",
            name, getRole(), skill, salary);
    }
}