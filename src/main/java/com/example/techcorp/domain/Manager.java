package com.example.techcorp.domain;

/**
 * Manager: produces 3× skill in work units per turn.
 * Managers coordinate teams effectively, multiplying overall project throughput.
 */
public class Manager extends Employee {

    public Manager(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    /**
     * Managers have the highest work multiplier due to team coordination.
     * Work output = skill × 3.
     */
    @Override
    public int work() {
        return getSkill() * 3;
    }

    @Override
    public String getRole() { return "Manager"; }
}