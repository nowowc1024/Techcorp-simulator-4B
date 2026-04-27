package com.example.techcorp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a software project that employees are assigned to.
 * Tracks progress through a status lifecycle:
 * PLANNED → IN_PROGRESS → FINISHED/CANCELLED, with ON_HOLD pause/resume.
 */
public class Project {

    private final String         name;
    private final int            requiredWork;
    private int                  progress;
    private ProjectStatus        status;
    private final List<Employee> employees;

    public Project(String name, int requiredWork) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Project name cannot be blank.");
        if (requiredWork <= 0)
            throw new IllegalArgumentException("Required work must be positive.");
        this.name         = name;
        this.requiredWork = requiredWork;
        this.progress     = 0;
        this.status       = ProjectStatus.PLANNED;
        this.employees    = new ArrayList<>();
    }

    // ── Employee assignment ──────────────────────────────────────────────────

    public void addEmployee(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("Employee cannot be null.");
        if (status == ProjectStatus.FINISHED || status == ProjectStatus.CANCELLED)
            throw new IllegalStateException(
                "Cannot assign employees to a " + status + " project.");
        if (employees.contains(employee))
            throw new IllegalStateException(
                employee.getName() + " is already assigned to this project.");
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (!employees.remove(employee))
            throw new IllegalStateException(
                employee.getName() + " is not assigned to this project.");
    }

    // ── Status transitions ───────────────────────────────────────────────────

    public void start() {
        if (status != ProjectStatus.PLANNED)
            throw new IllegalStateException(
                "Only PLANNED projects can be started. Current status: " + status);
        status = ProjectStatus.IN_PROGRESS;
    }

    public void pause() {
        if (status != ProjectStatus.IN_PROGRESS)
            throw new IllegalStateException(
                "Only IN_PROGRESS projects can be paused. Current status: " + status);
        status = ProjectStatus.ON_HOLD;
    }

    public void resume() {
        if (status != ProjectStatus.ON_HOLD)
            throw new IllegalStateException(
                "Only ON_HOLD projects can be resumed. Current status: " + status);
        status = ProjectStatus.IN_PROGRESS;
    }

    public void cancel() {
        if (status == ProjectStatus.FINISHED || status == ProjectStatus.CANCELLED)
            throw new IllegalStateException("Project is already " + status + ".");
        status = ProjectStatus.CANCELLED;
    }

    // ── Work ─────────────────────────────────────────────────────────────────

    /**
     * Advances work by one turn.
     * Each assigned employee contributes via work() — polymorphic dispatch
     * through the Workable interface.
     */
    public void workOneTurn() {
        if (status != ProjectStatus.IN_PROGRESS) return;
        for (Employee e : employees) {
            progress += e.work();
        }
        if (progress >= requiredWork) {
            progress = requiredWork;
            status   = ProjectStatus.FINISHED;
        }
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String        getName()         { return name; }
    public ProjectStatus getStatus()       { return status; }
    public int           getProgress()     { return progress; }
    public int           getRequiredWork() { return requiredWork; }
    public boolean       isFinished()      { return status == ProjectStatus.FINISHED; }
    public boolean       isCancelled()     { return status == ProjectStatus.CANCELLED; }
    public boolean       isActive()        { return status == ProjectStatus.IN_PROGRESS; }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public int getCompletionPercent() {
        return (int) ((progress / (double) requiredWork) * 100);
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-11s | %3d%% (%d/%d)",
            name, status, getCompletionPercent(), progress, requiredWork);
    }
}