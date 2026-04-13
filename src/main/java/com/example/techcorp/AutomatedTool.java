package com.example.techcorp;
import com.example.techcorp.domain.Workable;

public class AutomatedTool implements Workable {
    private String name;
    private int productivity;

    public AutomatedTool(String name, int productivity) {
        if (name == null || name.isBlank())
        throw new IllegalArgumentException("Tool name cannot be blank.");
        if (productivity < 0)
        throw new IllegalArgumentException("Productivity cannot be negative.");
        this.name = name;
        this.productivity = productivity;
    }

    @Override
    public int work() { return productivity; }
   
    public String getName() { return name; }
    
    public void printInfo() {
        System.out.println("Tool: " + name + " | Productivity: " + productivity);
    }
}