package com.example.techcorp;
import com.example.techcorp.domain.Workable;

public class FreelancerBot implements Workable {

    private String name;
    private int outputPerTurn;

    public FreelancerBot(String name, int outputPerTurn) {
        if (name == null || name.isBlank()) 
            throw new IllegalArgumentException("FreelancerBot name cannot be blank.");
        if (outputPerTurn < 0)
            throw new IllegalArgumentException("Output per turn cannot be negative.");
        this.name = name;
        this.outputPerTurn = outputPerTurn;
    }
    @Override
    public int work() { return outputPerTurn; }
    
    public String getName() { return name; }
    
    public void printInfo() {
        System.out.println("FreelancerBot: " + name
            + " | Output/turn: " + outputPerTurn);
    }
}