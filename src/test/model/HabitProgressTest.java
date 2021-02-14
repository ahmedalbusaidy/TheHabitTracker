package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testConstructor() {
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
}
