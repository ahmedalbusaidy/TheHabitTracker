package model;

import java.util.ArrayList;

/*
 * This class represents the list of habits
 */
public class HabitList {
    private ArrayList<Habit> listOfHabits;  //list containing all of the habits

    /*
     * EFFECTS: creates an empty list of habits
     */
    public HabitList() {
        listOfHabits = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS:  add habit to listOfHabits if it does not already exists and return true, otherwise return false
     */
    public boolean addHabit(Habit habit) {
        if (!listOfHabits.contains(habit)) {
            listOfHabits.add(habit);
            return true;
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes habit from listOfHabits if it exists and return true, otherwise return false
     */
    public boolean removeHabit(Habit habit) {
        if (listOfHabits.contains(habit)) {
            listOfHabits.remove(habit);
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: returns true if listOfHabits contains habit, otherwise returns false
     */
    public boolean containsHabit(Habit habit) {
        return listOfHabits.contains(habit);
    }

    /*
     * REQUIRES: listOfHabits contains at least one element
     * EFFECTS:  It prints a log of the habit at index - 1 on listOfHabits. This log includes:
     *              - habit name
     *              - current streak
     *              - highest streak
     *              - commitment target
     *              - total committed days
     *              - days left to reach the target
     */
    public void printSummaryLog(int index) {
        Habit habit = listOfHabits.get(index);
        System.out.println(habit.toString());
    }

    /*
     * EFFECTS:  returns listOfHabits
     */
    public ArrayList<Habit> getListOfHabits() {
        return listOfHabits;
    }
}
