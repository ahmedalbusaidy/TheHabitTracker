package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// This class represents the list of habits
public class HabitList implements Writable {
    private ArrayList<Habit> listOfHabits;  //list containing all of the habits

    // EFFECTS: creates an empty list of habits
    public HabitList() {
        listOfHabits = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  add habit to listOfHabits if it does not already exists and return true;
    //           otherwise return false
    public boolean addHabit(Habit habit) {
        if (!listOfHabits.contains(habit)) {
            listOfHabits.add(habit);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS:  removes habit from listOfHabits if it exists and return true;
    //           otherwise return false
    public boolean removeHabit(Habit habit) {
        if (listOfHabits.contains(habit)) {
            listOfHabits.remove(habit);
            return true;
        }
        return false;
    }

    // EFFECTS:  returns true if listOfHabits contains habit;
    //           otherwise returns false
    public boolean containsHabit(Habit habit) {
        return listOfHabits.contains(habit);
    }

    // REQUIRES: listOfHabits contains at least one element
    // EFFECTS:  prints summary log of the habit at index = index from listOfHabits
    public void printSummaryLog(int index) {
        Habit habit = listOfHabits.get(index);
        System.out.println(habit.toString());
    }

    // EFFECTS: returns JSONObject json, transforming this to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfHabits", listOfHabitsToJson());
        return json;
    }

    // EFFECTS: returns habits in this listOfHabits as a JSON array
    private JSONArray listOfHabitsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habit h : listOfHabits) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    // EFFECTS:  returns listOfHabits
    public ArrayList<Habit> getListOfHabits() {
        return listOfHabits;
    }
}
