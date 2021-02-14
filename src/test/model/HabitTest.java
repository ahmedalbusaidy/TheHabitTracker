package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * This class tests the Habit class
 */
public class HabitTest {
    private String habitName;           //name of the habit
    private int commitmentTarget;       //number of days to commit per month
    private Date startDate;             //start date i.e. the date habit was added
    private Habit running;
    private String variableName;

    @BeforeEach
    public void setup() {
        habitName = "running";
        commitmentTarget = 30;
        running = new Habit(habitName, commitmentTarget);
    }

    @Test
    public void testConstructor() {
        assertEquals(habitName, running.getHabitName());
        assertEquals(commitmentTarget, running.getCommitmentTarget());
        assertEquals(0, running.getTotalCommittedDays());
    }

    @Test
    public void testSetHabitName() {
        habitName = "Running for 30 mins";
        running.setHabitName(habitName);
        assertEquals(habitName, running.getHabitName());
    }

    @Test
    public void testSetCommitmentTarget() {
        commitmentTarget = 60;
        running.setCommitmentTarget(commitmentTarget);
        assertEquals(commitmentTarget, running.getCommitmentTarget());
    }

    @Test
    public void testSetStartDate() {
        startDate = new Date();
        running.setStartDate(startDate);
        assertEquals(startDate, running.getStartDate());
    }

    @Test
    public void testResetProgress() {
        variableName = "totalCommittedDays";
        running.increment(variableName);
        running.increment(variableName);

        variableName = "currentStreak";
        running.increment(variableName);
        running.increment(variableName);

        variableName = "highestStreak";
        running.increment(variableName);
        running.increment(variableName);

        running.resetProgress();
        assertEquals(0, running.getTotalCommittedDays());
        assertEquals(0, running.getCurrentStreak());
        assertEquals(0, running.getHighestStreak());
    }

    @Test
    public void testIncrementTotalCommittedDays() {
        variableName = "totalCommittedDays";

        running.increment(variableName);
        assertEquals(1, running.getTotalCommittedDays());

        running.increment(variableName);
        assertEquals(2, running.getTotalCommittedDays());
    }

    @Test
    public void testIncrementCurrentStreak() {
        variableName = "currentStreak";

        running.increment(variableName);
        assertEquals(1, running.getCurrentStreak());

        running.increment(variableName);
        assertEquals(2, running.getCurrentStreak());
    }

    @Test
    public void testIncrementHighestStreak() {
        variableName = "highestStreak";

        running.increment(variableName);
        assertEquals(1, running.getHighestStreak());

        running.increment(variableName);
        assertEquals(2, running.getHighestStreak());
    }

    @Test
    public void testDecrementTotalCommittedDays() {
        variableName = "totalCommittedDays";
        running.increment(variableName);
        running.increment(variableName);

        running.decrement(variableName);
        assertEquals(1, running.getTotalCommittedDays());

        running.decrement(variableName);
        assertEquals(0, running.getTotalCommittedDays());

        running.decrement(variableName);
        assertEquals(0, running.getTotalCommittedDays());
    }

    @Test
    public void testDecrementCurrentStreak() {
        variableName = "currentStreak";
        running.increment(variableName);
        running.increment(variableName);

        running.decrement(variableName);
        assertEquals(1, running.getCurrentStreak());

        running.decrement(variableName);
        assertEquals(0, running.getCurrentStreak());

        running.decrement(variableName);
        assertEquals(0, running.getCurrentStreak());
    }

    @Test
    public void testDecrementHighestStreak() {
        variableName = "highestStreak";
        running.increment(variableName);
        running.increment(variableName);

        running.decrement(variableName);
        assertEquals(1, running.getHighestStreak());

        running.decrement(variableName);
        assertEquals(0, running.getHighestStreak());

        running.decrement(variableName);
        assertEquals(0, running.getHighestStreak());
    }




}
