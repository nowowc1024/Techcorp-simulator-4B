package com.example.techcorp.engine;

/**
 * Tracks the player's score throughout the game.
 *
 * Scoring rules:
 *   +200 points — each project completed
 *   − 50 points — each project cancelled
 *   + 50 points — finishing all projects before the turn limit
 *   − 10 points — each turn taken (rewards efficiency)
 */
public class Score {

    private int points;

    public Score() {
        this.points = 0;
    }

    public void projectCompleted() { points += 200; }
    public void projectCancelled() { points -= 50;  }
    public void turnTaken()        { points -= 10;  }
    public void earlyFinishBonus() { points += 50;  }

    public int getPoints() { return points; }

    @Override
    public String toString() {
        return "Score: " + points + " pts";
    }
}