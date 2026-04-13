package com.example.techcorp;
import com.example.techcorp.domain.Employee;
public class Lesson6Main {
public static void main(String[] args) {
System.out.println("=== Exercise 4: Catching Exceptions ===");
// Valid input
try {
int cost = Exercises.calculateMonthlyCost(5, 4000);
System.out.println("Monthly cost (valid): " + cost);
} catch (IllegalArgumentException e) {
System.out.println("Error: " + e.getMessage());
}
// Invalid: zero employees
try {
int cost = Exercises.calculateMonthlyCost(0, 4000);
System.out.println("Monthly cost: " + cost);
} catch (IllegalArgumentException e) {
System.out.println("Caught error: " + e.getMessage());
}
// Invalid: negative salary
try {
int cost = Exercises.calculateMonthlyCost(5, -500);
System.out.println("Monthly cost: " + cost);
} catch (IllegalArgumentException e) {
System.out.println("Caught error: " + e.getMessage());
}
System.out.println();
System.out.println("=== Exercise 5: Divide with Precondition ===");
try {
System.out.println("10 / 2 = " + Exercises.divide(10, 2));
System.out.println("10 / 0 = " + Exercises.divide(10, 0));
} catch (IllegalArgumentException e) {
System.out.println("Caught error: " + e.getMessage());
}
System.out.println();
System.out.println("=== Employee Constructor Validation ===");
try {
Employee emp = new Employee("Anna", 8, 7000);
System.out.println("Created: " + emp);
} catch (IllegalArgumentException e) {
System.out.println("Error: " + e.getMessage());
}
try {
Employee emp = new Employee("", 5, 3000);
System.out.println("Created: " + emp);
} catch (IllegalArgumentException e) {
System.out.println("Caught error: " + e.getMessage());
}
System.out.println();
System.out.println("=== ValidationExample ===");
ValidationExample.main(args);
System.out.println();
System.out.println("=== Operator Examples ===");
OperatorExamples.main(args);
}
}
