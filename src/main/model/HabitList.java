package model;

import java.util.ArrayList;

public class HabitList {
    private ArrayList<Habit> listOfHabits;  //list containing all of the habits

    /*
     * EFFECTS: creates an empty list of habits
     */
    public HabitList() {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS:  add habit to listOfHabits
     */
    public void addHabit(Habit habit) {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes habit from listOfHabits
     */
    public void removeHabit(Habit habit) {
        //stub
    }

    /*
     * EFFECTS: returns true if listOfHabits contains habit, otherwise returns false
     */
    public boolean containsHabit(Habit habit) {
        return false;   //stub
    }

    /*
     * REQUIRES: listOfHabits contains at least one element
     * MODIFIES: this and Habit
     * EFFECTS:  for each habit in the listOfHabits, the progress is incremented by one
     */
    public void recordProgress(ArrayList<Habit> listOfHabits) {
        //stub
    }

    /*
     * REQUIRES: listOfHabits contains at least one element
     * MODIFIES: this and Habit
     * EFFECTS:  for each habit in the listOfHabits, the progress is reset to zero
     */
    public void resetLog(ArrayList<Habit> listOfHabits) {
        //stub
    }

    /*
     * REQUIRES: listOfHabits contains at least one element
     * EFFECTS:  for each habit in the listOfHabits, it prints its log which includes:
     *              - habit name
     *              - commitment target
     *              - progress
     *              - days left to reach the target
     *              - maximum commitment in a row
     */
    public String printLog(ArrayList<Habit> listOfHabits) {
        return null;    //stub
    }
}
