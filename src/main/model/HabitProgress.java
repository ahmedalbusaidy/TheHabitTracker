package model;

import java.util.ArrayList;
import java.util.Date;

/*
 * This class stores a log of the dates committed for each Habit
 */
public class HabitProgress {
    private ArrayList<Date> datesCommitted;
    private boolean isRecorded = false;
    private boolean isCurrentStreakBroken = false;

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
    public void addDate(Date date) {
        datesCommitted.add((date));
        isRecorded = true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes last date added to this
     */
    public void removeDate() {
        datesCommitted.remove(datesCommitted.size() - 1);
        isRecorded = false;
    }

    /*
     * EFFECTS: returns true if dateOfToday equals dateLastRecorded;
     *          otherwise returns false
     */
    public boolean isRecorded() {
        String dateOfToday = String.format("%tA %<tB %<te, %<tY%n", new Date());
        String dateLastRecorded = "";
        if (datesCommitted.size() != 0) {
            dateLastRecorded = String.format("%tA %<tB %<te, %<tY%n",
                    datesCommitted.get(datesCommitted.size() - 1));
        }

        return dateLastRecorded.equals(dateOfToday);
    }

    /*
     * EFFECTS: returns datesCommitted
     */
    public ArrayList<Date> getDatesCommitted() {
        return datesCommitted;
    }

    /*
     * EFFECTS: returns true if current streak is broken, otherwise return false
     */
    public boolean isCurrentStreakBroken() {

        if (datesCommitted.size() != 0) {
            if (getDateDifference() > 1) {
                isCurrentStreakBroken = true;
            }
        }
        return false;
    }

    /*
     * EFFECTS: returns the number of days difference between two dates
     */
    public long getDateDifference() {
        double numberOfMilliSecondsInDay = 8.64e+7;
        long diffInDays = 0;
        if (datesCommitted.size() != 0) {
            diffInDays = Math.round((new Date().getTime() - datesCommitted.get(datesCommitted.size() - 1).getTime())
                    / numberOfMilliSecondsInDay);
        }
        return diffInDays;
    }

}

