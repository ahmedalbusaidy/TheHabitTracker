package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitTest {
    private String habitName;       //name of the habit
    private int commitmentTarget;   //number of days to commit per month
    private int progress;           //counter: number of days committed
    private Date startDate;         //start date i.e. the date habit was added
    private Habit running;

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

    //TODO: if it was decided to keep setProgress method, implement the test suite for it
    //@Test
    //public void testSetProgress() {}

    @Test
    public void testSetStartDate() {
        startDate = new Date();
        running.setStartDate(startDate);
        assertEquals(startDate, running.getStartDate());
    }

    @Test
    public void testIncrementTotalCommittedDays() {
        running.incrementTotalCommittedDays();
        assertEquals(1, running.getTotalCommittedDays());
        running.incrementTotalCommittedDays();
        assertEquals(2, running.getTotalCommittedDays());
    }

    @Test
    public void testDecrementTotalCommittedDays() {
        running.incrementTotalCommittedDays();
        running.incrementTotalCommittedDays();

        running.decrementTotalCommittedDays();
        assertEquals(1, running.getTotalCommittedDays());

        running.decrementTotalCommittedDays();
        assertEquals(0, running.getTotalCommittedDays());

        running.decrementTotalCommittedDays();
        assertEquals(0, running.getTotalCommittedDays());
    }




}
