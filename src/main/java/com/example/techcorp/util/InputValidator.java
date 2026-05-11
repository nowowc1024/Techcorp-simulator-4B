package com.example.techcorp.util;

/**
 * Utility class providing static validation helpers.
 *
 * Centralises common input-checking logic so that domain classes
 * and UI code can delegate to a single, testable source of truth
 * rather than repeating the same checks in multiple places.
 *
 * All methods throw IllegalArgumentException for invalid input,
 * consistent with the validation contract used throughout the project.
 */
public class InputValidator {

    // Private constructor — this is a utility class, not meant to be instantiated.
    private InputValidator() {}

    /**
     * Validates that a String is not null and not blank.
     */
    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(fieldName + " cannot be null or blank.");
    }

    /**
     * Validates that a number is strictly positive (greater than zero).
     */
    public static void requirePositive(double value, String fieldName) {
        if (value <= 0)
            throw new IllegalArgumentException(fieldName + " must be greater than zero.");
    }

    /**
     * Validates that a number is non-negative (zero or greater).
     */
    public static void requireNonNegative(double value, String fieldName) {
        if (value < 0)
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
    }

    /**
     * Validates that a menu choice falls within an expected range.
     */
    public static void requireInRange(int choice, int min, int max) {
        if (choice < min || choice > max)
            throw new IllegalArgumentException(
                "Choice must be between " + min + " and " + max + ". Got: " + choice);
    }

    /**
     * Validates that an object reference is not null.
     */
    public static void requireNotNull(Object value, String fieldName) {
        if (value == null)
            throw new IllegalArgumentException(fieldName + " cannot be null.");
    }

    /**
     * Validates that a salary meets the minimum threshold.
     */
    public static void requireMinimumSalary(double salary, double minimum) {
        if (salary < minimum)
            throw new IllegalArgumentException(
                "Salary cannot be below minimum: " + minimum + ". Got: " + salary);
    }
}
