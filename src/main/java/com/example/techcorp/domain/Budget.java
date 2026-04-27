package com.example.techcorp.domain;

/**
 * Encapsulates company finances.
 * All monetary operations are validated to prevent negative balance or invalid amounts.
 */
public class Budget {

    private double balance;

    public Budget(double initialBalance) {
        if (initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        this.balance = initialBalance;
    }

    /**
     * Adds funds to the budget (e.g. project revenue, investment).
     */
    public void credit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Credit amount must be positive.");
        balance += amount;
    }

    /**
     * Deducts funds from the budget (e.g. salaries, costs).
     * Throws if there are insufficient funds.
     */
    public void debit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Debit amount must be positive.");
        if (amount > balance)
            throw new IllegalStateException(
                "Insufficient funds. Balance: " + balance + ", requested: " + amount);
        balance -= amount;
    }

    public double  getBalance()           { return balance; }
    public boolean canAfford(double amt)  { return amt <= balance; }

    @Override
    public String toString() {
        return String.format("Budget{ balance: %,.2f }", balance);
    }
}