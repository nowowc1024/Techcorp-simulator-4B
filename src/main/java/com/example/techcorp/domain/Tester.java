package com.example.techcorp.domain;
import com.example.techcorp.domain.Employee;

public class Tester extends Employee {

    public Tester(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill();
    }
}