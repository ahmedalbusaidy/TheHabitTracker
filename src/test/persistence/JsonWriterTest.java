package persistence;

import model.Habit;
import model.HabitList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HabitList habitList = new HabitList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHabitList() {
        try {
            HabitList habitList = new HabitList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHabitList.json");
            writer.open();
            writer.write(habitList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHabitList.json");
            habitList = reader.read();
            assertEquals(0, habitList.getListOfHabits().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHabitList() {
        try {
            HabitList habitList = new HabitList();

            Habit reading = new Habit("reading", 30);
            habitList.addHabit(reading);
            reading.setTotalCommittedDays(2);
            reading.setCurrentStreak(2);
            reading.setHighestStreak(2);
            reading.setStartDate(new Date(2021 - 1900, 2, 5));
            reading.setStreaksIncreasingTogether(true);

            Habit running = new Habit("running", 20);
            habitList.addHabit(running);
            running.setTotalCommittedDays(3);
            running.setCurrentStreak(3);
            running.setHighestStreak(3);
            running.setStartDate(new Date(2021 - 1900, 2, 4));
            running.setStreaksIncreasingTogether(true);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHabitList.json");
            writer.open();
            writer.write(habitList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHabitList.json");
            habitList = reader.read();
            List<Habit> listOfHabits = habitList.getListOfHabits();
            assertEquals(2, listOfHabits.size());
            checkHabit("reading", 30, 2, 2, 2,
                    new Date(2021 - 1900, 2, 5), listOfHabits.get(0).getHabitProgress(),
                    true, listOfHabits.get(0));

            checkHabit("running", 20, 3, 3, 3,
                    new Date(2021 - 1900, 2, 4), listOfHabits.get(1).getHabitProgress(),
                    true, listOfHabits.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
