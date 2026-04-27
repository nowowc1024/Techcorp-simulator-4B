package com.example.techcorp.domain;

/**
 * Developer: produces 2× skill in work units per turn.
 * Represents the primary coding workforce of the company.
 */
public class Developer extends Employee {

    public Developer(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    /**
     * Developers are the most productive workers for project progress.
     * Work output = skill × 2.
     */
    @Override
    public int work() {
        return getSkill() * 2;
    }

    @Override
    public String getRole() { return "Developer"; }
}