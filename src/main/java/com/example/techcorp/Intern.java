package com.example.techcorp;
import com.example.techcorp.domain.Employee;
import com.example.techcorp.domain.Employee;

public class Intern extends Employee {
    public Intern(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 4; // interns contribute a quarter of their skill
    }
}