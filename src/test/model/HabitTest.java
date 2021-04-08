package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// This class tests the Habit class
public class HabitTest {
    private String habitName;
    private int commitmentTarget;
    private Date startDate;
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
        assertFalse(running.getStreaksIncreasingTogether());
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
    public void testSetCurrentStreak() {
        running.setCurrentStreak(5);
        assertEquals(5, running.getCurrentStreak());
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

        Date date = new Date();
        running.setStartDate(date);
        running.resetProgress();
        assertEquals(0, running.getTotalCommittedDays());
        assertEquals(0, running.getCurrentStreak());
        assertEquals(0, running.getHighestStreak());
        assertFalse(running.getHabitProgress().getDatesCommitted().contains(date));
        assertNotEquals(startDate, running.getStartDate());
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
    public void testIncrementCurrentStreakGreaterThanHighestStreak() {
        variableName = "currentStreak";

        running.increment(variableName);
        assertEquals(1, running.getCurrentStreak());
        assertEquals(1, running.getHighestStreak());
        assertTrue(running.streaksIncreasingTogether);

        running.increment(variableName);
        assertEquals(2, running.getCurrentStreak());
        assertEquals(2, running.getHighestStreak());
        assertTrue(running.streaksIncreasingTogether);
    }

    @Test
    public void testIncrementCurrentStreakLessThanOrEqualToHighestStreak() {
        variableName = "currentStreak";

        running.increment(variableName);
        running.increment(variableName);
        running.increment(variableName);

        running.setCurrentStreak(0);
        running.increment(variableName);
        assertEquals(1, running.getCurrentStreak());
        assertEquals(3, running.getHighestStreak());

        running.increment(variableName);
        assertEquals(2, running.getCurrentStreak());
        assertEquals(3, running.getHighestStreak());

        running.increment(variableName);
        assertEquals(3, running.getCurrentStreak());
        assertEquals(3, running.getHighestStreak());

        running.increment(variableName);
        assertEquals(4, running.getCurrentStreak());
        assertEquals(4, running.getHighestStreak());
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
        variableName = "currentStreak";
        running.increment(variableName);
        running.increment(variableName);

        variableName = "highestStreak";
        running.decrement(variableName);
        assertEquals(1, running.getHighestStreak());

        running.decrement(variableName);
        assertEquals(0, running.getHighestStreak());

        running.decrement(variableName);
        assertEquals(0, running.getHighestStreak());
    }

    @Test
    public void testDecrementNothingTrue() {
        variableName = "totalCommittedDays";
        running.increment(variableName);
        running.increment(variableName);

        variableName = "currentStreak";
        running.increment(variableName);
        running.increment(variableName);

        variableName = "nothingTrue";
        running.decrement(variableName);

        assertEquals(2, running.getTotalCommittedDays());
        assertEquals(2, running.getCurrentStreak());
        assertEquals(2, running.getHighestStreak());

    }

    @Test
    public void testToString() {
        assertTrue(running.toString().contains("running                  0                  0                  "
                + "30         0                  30            "));
    }


}
