package model;

import java.util.Date;

public class Habit {
    private String habitName;           //name of the habit
    private int commitmentTarget;       //number of days to commit per month
    private int totalCommittedDays;     //counter: total number of days committed
    private static Date startDate;      //start date i.e. the date habit was added

    /*
     * REQUIRES: habitName has a non-zero length; commitmentTarget >= 0
     * EFFECTS:  name of the habit is set to habitName; commitment target is set to commitmentTarget;
     *           totalCommittedDays is set to zero;
     */
    public Habit(String habitName, int commitmentTarget) {
        this.habitName = habitName;
        this.commitmentTarget = commitmentTarget;
        totalCommittedDays = 0;
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

    //TODO: decide whether this method is needed or not
    /*
     * REQUIRES: progress >= 0
     * MODIFIES: this
     * EFFECTS:  sets this.progress to progress
     */
    /*public void setProgress() {
        //stub
    }*/

    /*
     * MODIFIES: this
     * EFFECTS:  sets startDate to current date
     */
    public void setStartDate(Date date) {
        startDate = date;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  increment total committed days by one
     */
    public void incrementTotalCommittedDays() {
        totalCommittedDays++;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  if totalCommittedDays > 0: decrement total committed days by one,
     *           otherwise do nothing
     */
    public void decrementTotalCommittedDays() {
        if (totalCommittedDays > 0) {
            totalCommittedDays--;
        }
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
     * EFFECTS: return total committed days
     */
    public int getTotalCommittedDays() {
        return totalCommittedDays;
    }

    /*
     * EFFECTS: return startDate
     */
    public Date getStartDate() {
        return startDate;
    }
}
