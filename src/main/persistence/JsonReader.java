package persistence;

import model.Habit;
import model.HabitList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.stream.Stream;

import model.HabitProgress;
import org.json.*;

// Represents a reader that reads habitList from JSON data stored in file.
// This class is modelled on the code from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads habitList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HabitList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHabitList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses habitList from JSON object and returns it
    private HabitList parseHabitList(JSONObject jsonObject) {
        HabitList habitList = new HabitList();
        addHabits(habitList, jsonObject);
        return habitList;
    }

    // MODIFIES: habitList
    // EFFECTS: parses habits from JSON object and adds them to habitList
    private void addHabits(HabitList habitList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfHabits");
        for (Object json : jsonArray) {
            JSONObject nextHabit = (JSONObject) json;
            addHabit(habitList, nextHabit);
        }
    }

    // MODIFIES: habitList
    // EFFECTS: parses habit from JSON object and adds it to habitList
    private void addHabit(HabitList habitList, JSONObject jsonObject) {
        String habitName = jsonObject.getString("habitName");
        int commitmentTarget = jsonObject.getInt("commitmentTarget");
        int totalCommittedDays = jsonObject.getInt("totalCommittedDays");
        int currentStreak = jsonObject.getInt("currentStreak");
        int highestStreak = jsonObject.getInt("highestStreak");
        Date startDate = new Date(jsonObject.getLong("startDateInMilliseconds"));
        boolean streaksIncreasingTogether = jsonObject.getBoolean("streaksIncreasingTogether");
        JSONObject habitProgress = jsonObject.getJSONObject("habitProgress");

        Habit habit = new Habit(habitName, commitmentTarget);
        habit.setTotalCommittedDays(totalCommittedDays);
        habit.setCurrentStreak(currentStreak);
        habit.setHighestStreak(highestStreak);
        habit.setStartDate(startDate);
        habit.setStreaksIncreasingTogether(streaksIncreasingTogether);
        addHabitProgress(habit, habitProgress);
        habitList.addHabit(habit);
    }

    // MODIFIES: habitList
    // EFFECTS: parses habitProgress from JSON object and adds it to habit
    private void addHabitProgress(Habit habit, JSONObject jsonObject) {
        boolean isRecorded = jsonObject.getBoolean("isRecorded");
        boolean isCurrentStreakBroken = jsonObject.getBoolean("isCurrentStreakBroken");
        HabitProgress habitProgress = new HabitProgress();

        JSONArray datesCommitted = jsonObject.getJSONArray("datesCommitted");

        for (int i = 0; i < datesCommitted.length(); i++) {
            Date dateCommitted = new Date(datesCommitted.getLong(i));
            habitProgress.getDatesCommitted().add(dateCommitted);
        }

        habitProgress.setIsRecorded(isRecorded);
        habitProgress.setIsCurrentStreakBroken(isCurrentStreakBroken);

        habit.setHabitProgress(habitProgress);
    }
}
