# TechCorp Simulator

A turn-based business decision-making game built in Java as a final project
for an introductory object-oriented programming course.

---

## Purpose

You manage a small technology company over up to 10 turns. Your goal is to
complete all software projects before the turn limit expires — without going
bankrupt paying your team's salaries.

---

## How to Run

**Requirements:** Java 17+, Maven

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.example.techcorp.Main"
```

Or from your IDE: run `Main.java`.

---

## Gameplay

Each turn you may take **one action** from the menu:

| # | Action |
|---|--------|
| 1 | Show full company status |
| 2 | Assign an employee to a project |
| 3 | Unassign an employee from a project |
| 4 | Start a PLANNED project |
| 5 | Pause an IN_PROGRESS project |
| 6 | Resume an ON_HOLD project |
| 7 | Cancel a project |
| 8 | **End turn** (work happens + salaries are paid) |
| 9 | Exit game |

Ending a turn triggers:
- Every IN_PROGRESS project advances (each assigned employee contributes work)
- Salaries are deducted from the company budget
- Score is updated

---

## Scoring

| Event | Points |
|-------|--------|
| Project completed | +200 |
| Project cancelled | −50 |
| Each turn taken | −10 |
| Early finish bonus | +50 |

---

## Win / Lose Conditions

- **WIN:** All projects reach FINISHED status.
- **LOSE (bankrupt):** Salary payment cannot be met.
- **LOSE (time):** Turn 10 ends with projects still unfinished.
- **LOSE (cancelled):** All projects resolved but none finished.

---

## Project Structure
src/main/java/com/example/techcorp/
├── Main.java
├── domain/
│   ├── Workable.java        ← interface: work() contract
│   ├── Employee.java        ← base class
│   ├── Developer.java       ← skill × 2 work output
│   ├── Tester.java          ← skill × 1 work output
│   ├── Manager.java         ← skill × 3 work output
│   ├── Project.java         ← lifecycle + progress tracking
│   ├── ProjectStatus.java   ← enum: PLANNED/IN_PROGRESS/ON_HOLD/FINISHED/CANCELLED
│   ├── Budget.java          ← encapsulated finances
│   └── Company.java         ← owns employees, projects, budget
├── engine/
│   ├── GameEngine.java      ← turn loop, action dispatch, win/lose logic
│   └── Score.java           ← scoring tracker
└── ui/
└── ConsoleUI.java       ← all display and input logic

---

## Features Implemented

- **Inheritance & polymorphism:** Developer, Tester, Manager each override work() with different multipliers, used polymorphically in Project.workOneTurn().
- **Interface:** Workable defines the work() contract implemented by all employee types.
- **Enum:** ProjectStatus models the full project lifecycle including CANCELLED.
- **Encapsulation:** Budget protects the balance; all Employee setters validate inputs.
- **Exception handling:** IllegalArgumentException for bad input; IllegalStateException for violated preconditions.
- **Turn-based game loop** with real player decisions each turn.
- **Scoring system** rewarding efficient, fast completion.
- **Separation of concerns:** domain / engine / ui packages are cleanly separated.

---

## Known Limitations

- No save/load functionality.
- Random events not implemented.
- Console only — no graphical interface.
