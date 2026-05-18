package com.example.techcorp.domain;

import com.example.techcorp.util.InputValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a software project that employees are assigned to.
 * Tracks progress through a status lifecycle:
 * PLANNED → IN_PROGRESS → FINISHED/CANCELLED, with ON_HOLD pause/resume.
 *
 * A project also carries a cash {@code reward}, paid to the company
 * when the project reaches FINISHED status.
 */
public class Project {

    /** Default reward used when none is specified: scales with required work. */
    private static final int DEFAULT_REWARD_PER_WORK = 2500;

    private final String         name;
    private final int            requiredWork;
    private final int            reward;
    private int                  progress;
    private ProjectStatus        status;
    private final List<Employee> employees;

    /**
     * Full constructor: lets the caller set an explicit cash reward.
     */
    public Project(String name, int requiredWork, int reward) {
        InputValidator.requireNonBlank(name, "Project name");
        InputValidator.requirePositive(requiredWork, "Required work");
        InputValidator.requirePositive(reward, "Reward");
        this.name         = name;
        this.requiredWork = requiredWork;
        this.reward       = reward;
        this.progress     = 0;
        this.status       = ProjectStatus.PLANNED;
        this.employees    = new ArrayList<>();
    }

    /**
     * Convenience constructor: keeps older two-argument calls working.
     * The reward defaults to requiredWork * DEFAULT_REWARD_PER_WORK.
     */
    public Project(String name, int requiredWork) {
        this(name, requiredWork, requiredWork * DEFAULT_REWARD_PER_WORK);
    }

    public void addEmployee(Employee employee) {
        InputValidator.requireNotNull(employee, "Employee");
        if (status == ProjectStatus.FINISHED || status == ProjectStatus.CANCELLED)
            throw new IllegalStateException(
                "Cannot assign employees to a " + status + " project.");
        if (employees.contains(employee))
            throw new IllegalStateException(
                employee.getName() + " is already assigned to this project.");
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        InputValidator.requireNotNull(employee, "Employee");
        if (!employees.remove(employee))
            throw new IllegalStateException(
                employee.getName() + " is not assigned to this project.");
    }

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

    public String        getName()         { return name; }
    public ProjectStatus getStatus()       { return status; }
    public int           getProgress()     { return progress; }
    public int           getRequiredWork() { return requiredWork; }
    public int           getReward()       { return reward; }
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