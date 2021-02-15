package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/*
 * This class tests the HabitList class
 */
public class HabitListTest {
    private HabitList habitList;
    private String habitName1;
    private String habitName2;
    int commitmentTarget1;
    int commitmentTarget2;
    Habit reading;
    Habit coding;


    @BeforeEach
    public void setup() {
        habitList = new HabitList();

        habitName1 = "reading for 1hr";
        commitmentTarget1 = 30;
        reading = new Habit(habitName1, commitmentTarget1);

        habitName2 = "coding for 2hrs";
        commitmentTarget2 = 60;
        coding = new Habit(habitName2, commitmentTarget2);

    }

    @Test
    public void testAddHabit() {
        boolean isAdded = false;

        isAdded = habitList.addHabit(reading);
        assertEquals(1, habitList.getListOfHabits().size());
        assertTrue(habitList.containsHabit(reading));
        assertEquals(true, isAdded);

        isAdded = habitList.addHabit(reading);
        assertEquals(1, habitList.getListOfHabits().size());
        assertEquals(false, isAdded);

        isAdded = habitList.addHabit(coding);
        assertEquals(2, habitList.getListOfHabits().size());
        assertTrue(habitList.containsHabit(coding));
        assertEquals(true, isAdded);
    }

    @Test
    public void testRemoveHabit() {
        boolean isRemoved = false;

        habitList.addHabit(reading);
        habitList.addHabit(coding);

        isRemoved = habitList.removeHabit(coding);
        assertEquals(1, habitList.getListOfHabits().size());
        assertFalse(habitList.containsHabit(coding));
        assertEquals(true, isRemoved);

        isRemoved = habitList.removeHabit(coding);
        assertEquals(1, habitList.getListOfHabits().size());
        assertEquals(false, isRemoved);

        isRemoved = habitList.removeHabit(reading);
        assertEquals(0, habitList.getListOfHabits().size());
        assertFalse(habitList.containsHabit(reading));
        assertEquals(true, isRemoved);
    }

    @Test
    public void testContainsHabit() {
        boolean doesContainHabit = false;
        habitList.addHabit(reading);

        doesContainHabit = habitList.containsHabit(reading);
        assertEquals(true, doesContainHabit);

        doesContainHabit = habitList.containsHabit(coding);
        assertEquals(false, doesContainHabit);
    }

    @Test
    public void testPrintSummaryLog() {
        habitList.addHabit(reading);

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        habitList.printSummaryLog(0);
        assertEquals(reading.toString() + "\n", outContent.toString());

        System.setOut(originalOut);


    }
}
