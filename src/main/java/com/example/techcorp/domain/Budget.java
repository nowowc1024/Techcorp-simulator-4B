package com.example.techcorp.domain;

public class Budget {

    private double balance;

    public Budget(double initialBalance) {
        if (initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        this.balance = initialBalance;
    }

    public void credit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Credit amount must be positive.");
        balance += amount;
    }

    public void debit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Debit amount must be positive.");
        if (amount > balance)
            throw new IllegalStateException("Insufficient funds. Balance: "
                + balance + ", requested: " + amount);
        balance -= amount;
    }

    public double getBalance()           { return balance; }
    public boolean canAfford(double amt) { return amt <= balance; }

    @Override
    public String toString() {
        return "Budget{balance=" + balance + "}";
    }
}
