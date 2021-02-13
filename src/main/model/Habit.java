package model;

import java.util.Date;

public class Habit {
    private String habitName;       //name of the habit
    private int commitmentTarget;   //number of days to commit per month
    private int progress;           //counter: number of days committed
    private static Date startDate;         //start date i.e. the date habit was added

    /*
     * REQUIRES: habitName has a non-zero length; commitmentTarget >= 0
     * EFFECTS:  name of the habit is set to habitName; commitment target is set to commitmentTarget;
     *           progress is set to zero;
     */
    public Habit(String habitName, int commitmentTarget) {
        this.habitName = habitName;
        this.commitmentTarget = commitmentTarget;
        progress = 0;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  sets this.habitName to habitName
     */
    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    /*
     * REQUIRES: commitmentTarget >= 0
     * MODIFIES: this
     * EFFECTS:  sets this.commitmentTarget to commitmentTarget
     */
    public void setCommitmentTarget(int commitmentTarget) {
        this.commitmentTarget = commitmentTarget;
    }

    /*
     * REQUIRES: progress >= 0
     * MODIFIES: this
     * EFFECTS:  sets this.progress to progress
     */
    public void setProgress() {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS:  sets startDate to current date
     */
    public void setStartDate(Date date) {
        startDate = date;
    }

    /*
     * MODIFIES: this
     * EFFECTS: increment progress by one
     */
    public void incrementProgress() {
        progress++;
    }

    /*
     * EFFECTS: returns habitName
     */
    public String getHabitName() {
        return habitName;
    }

    /*
     * EFFECTS: returns commitmentTarget
     */
    public int getCommitmentTarget() {
        return commitmentTarget;
    }

    /*
     * EFFECTS: return progress
     */
    public int getProgress() {
        return progress;
    }

    /*
     * EFFECTS: return startDate
     */
    public Date getStartDate() {
        return startDate;
    }
}
