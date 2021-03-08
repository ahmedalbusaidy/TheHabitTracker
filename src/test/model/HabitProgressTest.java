package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

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
        assertTrue(habit1.getHabitProgress().isRecorded());

        Habit habit2 = new Habit("walking", 30);
        habit2.increment("totalCommittedDays");
        habit2.increment("currentStreak");
        date = new Date(1995, 11, 17, 3, 24, 0);
        habit2.getHabitProgress().addDate(date);
        assertFalse(habit2.getHabitProgress().isRecorded());
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

        habit1.getHabitProgress().addDate(new Date());
        assertTrue(habit1.getHabitProgress().isRecorded());
        assertEquals(0, habit1.getHabitProgress().getDateDifference());

        habit1.getHabitProgress().addDate(new Date(date.getTime() - 2*24*60*60*1000));
        assertEquals(2, habit1.getHabitProgress().getDateDifference());
    }

    @Test
    public void testDatesCommittedToJson() {
        try {
            HabitList habitList = new HabitList();
            habitList.addHabit(habit1);
            habit1.getHabitProgress().addDate(new Date(date.getTime() - 2*24*60*60*1000));
            assertFalse(habit1.getHabitProgress().isRecorded());
            habit1.getHabitProgress().addDate(new Date(date.getTime() - 24*60*60*1000));
            assertFalse(habit1.getHabitProgress().isRecorded());
            habit1.getHabitProgress().addDate(new Date(date.getTime()));
            assertTrue(habit1.getHabitProgress().isRecorded());

            JSONArray datesCommitted = habit1.getHabitProgress().toJson().getJSONArray("datesCommitted");

            JsonWriter writer = new JsonWriter("./data/testDatesCommittedToJson.json");
            writer.open();
            writer.write(habitList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testDatesCommittedToJson.json");
            habitList = reader.read();

            assertEquals(date.getTime() - 2*24*60*60*1000, datesCommitted.get(0));
            assertEquals(date.getTime() - 24*60*60*1000, datesCommitted.get(1));
            assertEquals(date.getTime(), datesCommitted.get(2));

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

    }
}
