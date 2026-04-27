package com.example.techcorp.domain;

/**
 * Tester: produces 1× skill in work units per turn.
 * Testers contribute quality assurance work at a steadier, careful pace.
 */
public class Tester extends Employee {

    public Tester(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    /**
     * Testers work at their base skill level — methodical and thorough.
     * Work output = skill × 1.
     */
    @Override
    public int work() {
        return getSkill();
    }

    @Override
    public String getRole() { return "Tester"; }
}