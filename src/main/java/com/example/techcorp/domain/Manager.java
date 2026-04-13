package com.example.techcorp.domain;
import com.example.techcorp.domain.Employee;

public class Manager extends Employee {

    public Manager(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill();
    }
}