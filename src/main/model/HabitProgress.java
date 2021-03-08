package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Date;

// This class stores a log of the dates committed for each Habit
public class HabitProgress implements Writable {
    private ArrayList<Date> datesCommitted;             //list to store log of the dates committed to habit
    private boolean isRecorded = false;                 //Boolean value to check if habit is recorded
    private boolean isCurrentStreakBroken = false;

    // EFFECTS: constructs the datesCommitted ArrayList//
    public HabitProgress() {
        datesCommitted = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  sets isRecorded to bool//
    public void setIsRecorded(boolean bool) {
        this.isRecorded = bool;
    }

    // MODIFIES: this
    // EFFECTS:  sets isCurrentStreakBroken to bool//
    public void setIsCurrentStreakBroken(boolean bool) {
        this.isCurrentStreakBroken = bool;
    }

    // MODIFIES: this
    // EFFECTS: adds the date to datesCommitted list when progress is incremented;
    //          sets isRecorded = true//
    public void addDate(Date date) {
        datesCommitted.add((date));
        isRecorded = true;
    }

    // MODIFIES: this
    // EFFECTS: removes last date added to this
    public void removeDate() {
        datesCommitted.remove(datesCommitted.size() - 1);
        isRecorded = false;
    }

    // EFFECTS: returns true if dateOfToday equals dateLastRecorded;
    //          otherwise returns false
    public boolean isRecorded() {
        String dateOfToday = String.format("%tA %<tB %<te, %<tY%n", new Date());
        String dateLastRecorded = "";
        if (datesCommitted.size() != 0) {
            dateLastRecorded = String.format("%tA %<tB %<te, %<tY%n",
                    datesCommitted.get(datesCommitted.size() - 1));
        }

        return dateLastRecorded.equals(dateOfToday);
    }

    // EFFECTS: returns true if current streak is broken, otherwise return false
    public boolean isCurrentStreakBroken() {
        if (getDateDifference() > 1) {
            isCurrentStreakBroken = true;
        }
        return isCurrentStreakBroken;
    }

    // EFFECTS: returns the number of days difference between two dates
    public long getDateDifference() {
        long diffInDays = 0;
        if (datesCommitted.size() != 0) {
            diffInDays = ((new Date().getTime() - datesCommitted.get(datesCommitted.size() - 1).getTime())
                    / (1000 * 60 * 60 * 24)) % 365;
        }
        return diffInDays;
    }

    // EFFECTS: returns datesCommitted
    public ArrayList<Date> getDatesCommitted() {
        return datesCommitted;
    }

    // EFFECTS: returns JSONObject json, transforming this to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("isRecorded", isRecorded);
        json.put("isCurrentStreakBroken", isCurrentStreakBroken);
        json.put("datesCommitted", datesCommittedToJson());
        return json;
    }

    // EFFECTS: returns datesCommitted for this habit as a JSON array
    private JSONArray datesCommittedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Date d : datesCommitted) {
            jsonArray.put(d.getTime());
        }

        return jsonArray;
    }

}

