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

    @BeforeEach
    public void setup() {
        habitLog = new HabitProgress();
        date = new Date();

    }

    @Test
    public void testAddDate() {
        habitLog.addDate(date);

        assertTrue(habitLog.getDatesCommitted().contains(date));
    }

    @Test
    public void testRemoveDate() {
        habitLog.addDate(date);
        habitLog.removeDate();

        assertFalse(habitLog.getDatesCommitted().contains(date));
    }

    @Test
    public void testIsRecorded() {
        Habit habit1 = new Habit("running", 30);
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
}
