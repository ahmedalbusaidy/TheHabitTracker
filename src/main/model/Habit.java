//TODO: replace java.Date with java.time in the whole project

package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;

/*
 * This class represents a habit and its attributes
 */
public class Habit implements Writable {
    private String habitName;               //name of the habit
    private int commitmentTarget;           //number of days to commit per month
    private int totalCommittedDays;         //counter: total number of days committed
    private int currentStreak;              //the current streak of committed days in a row
    private int highestStreak;              //the highest streak of committed days in a row
    private Date startDate = new Date();    //start date i.e. the date habit was added
    private HabitProgress habitProgress;    //keep track of the progress by dates
    boolean streaksIncreasingTogether;      //Keep track if the two streaks are increasing together

    /*
     * REQUIRES: habitName has a non-zero length; commitmentTarget >= 0
     * MODIFIES: HabitProgress
     * EFFECTS:  name of the habit is set to habitName;
     *           commitment target is set to commitmentTarget;
     *           totalCommittedDays is set to zero;
     *           constructs HabitProgress object to store habit progress;
     *           sets streaksIncreasingTogether = false
     */
    public Habit(String habitName, int commitmentTarget) {
        this.habitName = habitName;
        this.commitmentTarget = commitmentTarget;
        totalCommittedDays = 0;
        habitProgress = new HabitProgress();
        streaksIncreasingTogether = false;
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

    //TODO: Newly added method, check if testing is needed
    /*
     * MODIFIES: this
     * EFFECTS:  sets totalCommittedDays to streak
     */
    public void setTotalCommittedDays(int totalCommittedDays) {
        this.totalCommittedDays = totalCommittedDays;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  sets currentStreak to streak
     */
    public void setCurrentStreak(int streak) {
        this.currentStreak = streak;
    }

    //TODO: Newly added method, check if testing is needed
    /*
     * MODIFIES: this
     * EFFECTS:  sets highestStreak to streak
     */
    public void setHighestStreak(int streak) {
        this.highestStreak = streak;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  sets startDate to date
     */
    public void setStartDate(Date date) {
        startDate = date;
    }

    //TODO: Newly added method, check if testing is needed
    /*
     * MODIFIES: this
     * EFFECTS:  sets habitProgress to habitProgress
     */
    public void setHabitProgress(HabitProgress habitProgress) {
        this.habitProgress = habitProgress;
    }

    //TODO: Newly added method, check if testing is needed
    /*
     * MODIFIES: this
     * EFFECTS:  sets streaksIncreasingTogether to bool
     */
    public void setStreaksIncreasingTogether(boolean bool) {
        this.streaksIncreasingTogether = bool;
    }

    /*
     * EFFECTS:  resets totalCommittedDays, currentStreak, and highestStreak to zero;
     *           sets the date to today's date
     */
    public void resetProgress() {
        totalCommittedDays = 0;
        currentStreak = 0;
        highestStreak = 0;
        startDate = new Date();
    }

    /*
     * REQUIRES: variableName equals either "totalCommittedDays", or "currentStreak", or "highestStreak"
     * MODIFIES: this
     * EFFECTS:  if case = "totalCommittedDays", increment totalCommittedDays by one;
     *           else if case = "currentStreak" increment currentStreak by one and
     *           if currentStreak > highestStreak then:
     *              - set highestStreak = currentStreak
     *              - set streaksIncreasingTogether = true
     */
    public void increment(String variableName) {
        switch (variableName) {
            case "totalCommittedDays":
                totalCommittedDays++;
                break;
            case "currentStreak":
                currentStreak++;
                if (currentStreak > highestStreak) {
                    highestStreak = currentStreak;
                    streaksIncreasingTogether = true;
                }
                break;
        }
    }

    /*
     * REQUIRES: variableName equals either "totalCommittedDays", or "currentStreak", or "highestStreak"
     * MODIFIES: this
     * EFFECTS:  if case = "totalCommittedDays" and totalCommittedDays > 0: decrement totalCommittedDays by one;
     *           else if case = "currentStreak" and currentStreak > 0: decrement currentStreak by one;
     *           else if case = "highestStreak" and highestStreak > 0: decrement highestStreak by one;
     */
    public void decrement(String variableName) {
        switch (variableName) {
            case "totalCommittedDays":
                if (totalCommittedDays > 0) {
                    totalCommittedDays--;
                }
                break;
            case "currentStreak":
                if (currentStreak > 0) {
                    currentStreak--;
                }
                break;
            case "highestStreak":
                if (highestStreak > 0) {
                    highestStreak--;
                }
                break;
        }
    }

    /*
     * EFFECTS: returns a string representation of the habit progress
     */
    @Override
    public String toString() {
        int daysToTarget = commitmentTarget - totalCommittedDays;
        String habitStr = String.format("%-25s%-19d%-19d%-11d%-19d%-14d", habitName, currentStreak,
                highestStreak, commitmentTarget, totalCommittedDays, daysToTarget);
        return habitStr;
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

    /*
     * EFFECTS: return currentStreak
     */
    public int getCurrentStreak() {
        return currentStreak;
    }

    /*
     * EFFECTS: return highestStreak
     */
    public int getHighestStreak() {
        return highestStreak;
    }

    /*
     * EFFECTS: return habitProgress
     */
    public HabitProgress getHabitProgress() {
        return habitProgress;
    }

    /*
     * EFFECTS: returns streaksIncreasingTogether
     */
    public boolean getStreaksIncreasingTogether() {
        return streaksIncreasingTogether;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("habitName", habitName);
        json.put("commitmentTarget", commitmentTarget);
        json.put("totalCommittedDays", totalCommittedDays);
        json.put("currentStreak", currentStreak);
        json.put("highestStreak", highestStreak);
        json.put("startDateInMilliseconds", startDate.getTime());
        json.put("commitmentTarget", commitmentTarget);
        json.put("streaksIncreasingTogether", streaksIncreasingTogether);
        json.put("habitProgress", habitProgress.toJson());
        return json;
    }


}
