package ui;

import model.Habit;
import model.HabitList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

// Represents the habit tracker application
public class HabitTrackerGUI {
    private static final String JSON_STORE = "./data/habitTracker.json";
    protected HabitList habitList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the tracker application
    public HabitTrackerGUI() throws FileNotFoundException {
        habitList = new HabitList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public HabitList getHabitList() {
        return habitList;
    }

    //MODIFIES: this
    //EFFECTS:  resets progress
    public void resetProgress(Habit habit) {
        habit.resetProgress();
    }

    //MODIFIES: this
    public void deleteHabit(Habit habit) {
        habitList.removeHabit(habit);
    }

    // EFFECTS: saves the habits list to file
    public void saveHabitsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(habitList);
            jsonWriter.close();
            System.out.println("Saved your habits list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the habits list from file
    public void loadHabitsList() {
        try {
            habitList = jsonReader.read();
        } catch (IOException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //REQUIRES: habitName to not be an empty string
    //MODIFIES: this
    //EFFECTS:  adds a new habit to the list of habits
    public void addHabitToListOfHabits(String habitName, int commitmentTarget) {
        Habit habit;
        habit = new Habit(habitName, commitmentTarget);
        habit.setStartDate(new Date());
        habitList.addHabit(habit);
    }


    //MODIFIES: this
    //EFFECTS: processes user command from recordModifyProgress method
    public void updateHabitProgressIsCompleted(Habit habit) {
        if (!habit.getHabitProgress().isRecorded()) {
            habit.increment("totalCommittedDays");
            habit.increment("currentStreak");
            habit.getHabitProgress().addDate(new Date());
        }
    }

    //MODIFIES: this
    //EFFECTS:  processes modify habit progress to not recorded
    public void updateHabitProgressNotCompleted(Habit habit) {
        if (habit.getHabitProgress().isRecorded()) {
            habit.decrement("totalCommittedDays");
            habit.decrement("currentStreak");
            if (habit.getStreaksIncreasingTogether()) {
                habit.decrement("highestStreak");
            }
            habit.getHabitProgress().removeDate();
        }
    }
}

