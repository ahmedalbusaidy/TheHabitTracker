package model;

public class Habit {
    String habitName;       //name of the habit
    int commitmentTarget;   //number of days to commit per month
    int progress;           //counter: number of days committed

    /*
     * REQUIRES: habitName has a non-zero length; commitmentTarget >= 0
     * EFFECTS:  name of the habit is set to habitName; commitment target is set to commitmentTarget;
     *           progress is set to zero
     */
    public Habit(String habitName, int commitmentTarget) {
        //Stub
    }

    /*
     * MODIFIES: this
     * EFFECTS:  sets this.habitName to habitName
     */
    public void setHabitName(String habitName) {
        //stub
    }

    /*
     * REQUIRES: commitmentTarget >= 0
     * MODIFIES: this
     * EFFECTS:  sets this.commitmentTarget to commitmentTarget
     */
    public void setCommitmentTarget(int commitmentTarget) {
        //stub
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
     * EFFECTS: increment progress by one
     */
    public void incrementProgress() {
        //stub
    }

    /*
     * EFFECTS: returns habitName
     */
    public String getHabitName() {
        return null;    //stub
    }

    /*
     * EFFECTS: returns commitmentTarget
     */
    public int getCommitmentTarget() {
        return 0;   //stub
    }

    /*
     * EFFECTS: return progress
     */
    public int getProgress() {
        return 0;   //stub
    }
}
