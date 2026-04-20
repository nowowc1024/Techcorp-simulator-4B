package com.example.techcorp.domain;

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
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank.");
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
            throw new IllegalArgumentException("Salary cannot be below minimum: " + MINIMUM_SALARY);
        this.salary = salary;
    }

    public String getName()   { return name; }
    public int    getSkill()  { return skill; }
    public double getSalary() { return salary; }

    @Override
    public int work() { return skill; }

    public String getRole() { return "Employee"; }

    public int getProductivity() { return skill; }

    public void printInfo() {
        System.out.println("Employee: " + name
            + ", Skill: " + skill + ", Salary: " + salary);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', skill=" + skill
            + ", salary=" + salary + "}";
    }
}
