package com.example.techcorp.domain;
import com.example.techcorp.domain.Employee;

public class Developer extends Employee {

    public Developer(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill();
    }
}