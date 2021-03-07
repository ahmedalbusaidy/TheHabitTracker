package persistence;

import model.Habit;
import model.HabitProgress;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkHabit(String habitName, int commitmentTarget, int totalCommittedDays,
                              int currentStreak, int highestStreak, Date startDate, HabitProgress habitProgress,
                              boolean streaksIncreasingTogether, Habit habit) {

        assertEquals(habitName, habit.getHabitName());
        assertEquals(commitmentTarget, habit.getCommitmentTarget());
        assertEquals(totalCommittedDays, habit.getTotalCommittedDays());
        assertEquals(currentStreak, habit.getCurrentStreak());
        assertEquals(startDate, habit.getStartDate());
        assertEquals(highestStreak, habit.getHighestStreak());
        assertEquals(habitProgress, habit.getHabitProgress());
        assertEquals(streaksIncreasingTogether, habit.getStreaksIncreasingTogether());
        //TODO: checkHabitProgress
    }

}
