package persistence;

import model.Habit;
import model.HabitList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HabitList habitList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHabitList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHabitList.json");
        try {
            HabitList habitList = reader.read();
            assertEquals(0, habitList.getListOfHabits().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHabitList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHabitList.json");
        try {
            HabitList habitList = reader.read();
            List<Habit> listOfHabits = habitList.getListOfHabits();
            assertEquals(2, listOfHabits.size());
            checkHabit("reading", 30, 2, 2, 2,
                    new Date(2021 - 1900, 2, 5), listOfHabits.get(0).getHabitProgress(),
                    true, listOfHabits.get(0));
            checkHabit("running", 20, 3, 3, 3,
                    new Date(2021 - 1900, 2, 4), listOfHabits.get(1).getHabitProgress(),
                    true, listOfHabits.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
