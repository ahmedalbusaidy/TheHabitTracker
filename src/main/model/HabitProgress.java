package model;

import java.util.ArrayList;
import java.util.Date;

/*
 * This class stores a log of the dates committed for each Habit
 */
public class HabitProgress {
    private ArrayList<Date> datesCommitted;

    /*
     * EFFECTS: constructs the datesCommitted method
     */
    public HabitProgress() {
        datesCommitted = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the date to datesCommitted list when progress is incremented
     */
    public void addDate() {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes last date added to this
     */
    public void removeDate() {
        //stub
    }

}
