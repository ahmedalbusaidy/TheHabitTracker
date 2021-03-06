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
    public HabitTrackerGUI() {
        habitList = new HabitList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS:  returns habitList
    public HabitList getHabitList() {
        return habitList;
    }

    //MODIFIES: this, Habit, HabitList, HabitProgress
    //EFFECTS:  resets progress
    public void resetProgress(Habit habit) {
        habit.resetProgress();
    }

    //MODIFIES: this, HabitList
    //EFFECTS:  delete habit from the list of habit
    public void deleteHabit(Habit habit) {
        habitList.removeHabit(habit);
    }

    //MODIFIES: this
    //EFFECTS:  saves the habits list to file
    //          displays an error message if FileNotFoundException is thrown
    public void saveHabitsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(habitList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "File Not Found! " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS:  loads the habits list from file
    //          displays an error message if IOException is thrown
    public void loadHabitsList() {
        try {
            habitList = jsonReader.read();
        } catch (IOException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Unable to read from file! " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this, Habit, HabitList
    //EFFECTS:  adds a new habit to the list of habits
    public void addHabitToListOfHabits(String habitName, int commitmentTarget) {
        Habit habit;
        habit = new Habit(habitName, commitmentTarget);
        habit.setStartDate(new Date());
        habitList.addHabit(habit);
    }


    //MODIFIES: this, Habit, HabitList, HabitProgress
    //EFFECTS:  records today's progress as completed so totalCommittedDays and currentStreak are incremented,
    //          and today's date is added to HabitProgress
    public void updateHabitProgressIsCompleted(Habit habit) {
        if (!habit.getHabitProgress().isRecorded()) {
            habit.increment("totalCommittedDays");
            habit.increment("currentStreak");
            habit.getHabitProgress().addDate(new Date());
        }
    }

    //MODIFIES: this, Habit, HabitList, HabitProgress
    //EFFECTS:  records today's progress as uncompleted:
    //          if habit.getHabitProgress().isRecorded() then:
    //              totalCommittedDays and currentStreak are decremented;
    //              if habit.getStreaksIncreasingTogether() then:
    //                  decrement highestStreak;
    //              remove today's date from HabitProgress
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

