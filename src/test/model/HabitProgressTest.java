package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/*
 * This class tests the HabitProgress class
 */
public class HabitProgressTest {
    private HabitProgress habitLog;
    private Date date;
    private Habit habit1;

    @BeforeEach
    public void setup() {
        habitLog = new HabitProgress();
        date = new Date();

        habit1 = new Habit("running", 30);
    }

    @Test
    public void testAddDate() {
        habitLog.addDate(date);

        assertTrue(habitLog.getDatesCommitted().contains(date));
        assertTrue(habitLog.isRecorded());
    }

    @Test
    public void testRemoveDate() {
        habitLog.addDate(date);
        habitLog.removeDate();

        assertFalse(habitLog.getDatesCommitted().contains(date));
        assertFalse(habitLog.isRecorded());
    }

    @Test
    public void testIsRecorded() {
        habit1.increment("totalCommittedDays");
        habit1.increment("currentStreak");
        habit1.getHabitProgress().addDate(date);
        assertEquals(true, habit1.getHabitProgress().isRecorded());

        Habit habit2 = new Habit("walking", 30);
        habit2.increment("totalCommittedDays");
        habit2.increment("currentStreak");
        date = new Date(1995, 11, 17, 3, 24, 0);
        habit2.getHabitProgress().addDate(date);
        assertEquals(false, habit2.getHabitProgress().isRecorded());
    }

    @Test
    public void testIsCurrentStreakBroken() {
        assertFalse(habitLog.isCurrentStreakBroken());
        habit1.increment("totalCommittedDays");
        habit1.increment("currentStreak");

        habit1.getHabitProgress().addDate(date);
        habit1.getHabitProgress().getDateDifference();
        assertFalse(habit1.getHabitProgress().isCurrentStreakBroken());

        habit1.getHabitProgress().addDate(new Date(2021 - 1900, 1, 12));
        assertTrue(habit1.getHabitProgress().isCurrentStreakBroken());
    }

    @Test
    public void testGetDateDifference() {
        assertEquals(0, habitLog.getDateDifference());

        habit1.increment("totalCommittedDays");
        habit1.increment("currentStreak");

        habit1.getHabitProgress().addDate(date);
        assertEquals(0, habit1.getHabitProgress().getDateDifference());

        habit1.getHabitProgress().addDate(new Date(2021 - 1900, 1, 12));
        assertEquals(2, habit1.getHabitProgress().getDateDifference());


    }
}
