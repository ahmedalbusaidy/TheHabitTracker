
package ui;

import model.Habit;
import model.HabitList;

import java.util.Date;
import java.util.Scanner;

public class HabitTrackerApp {
    private Scanner input;
    private HabitList habitList = new HabitList();
    boolean isRecorded = false;
    boolean isAnotherDay = false;

    // EFFECTS: runs the tracker application
    public HabitTrackerApp() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        System.out.println("\nWelcome: " + System.getProperty("user.name"));
        System.out.println("\nNow you can track your habits easily using The Habit Tracker, where you can add new"
                + "\nhabits to your list, set a commitment target, display your progress and much more!"
                + "\nHabit Tracking never been easier before!");
        while (keepGoing) {
            displayMenu();
            command = input.next();
            updateCurrentStreak();

            if (command.toLowerCase().equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        if (habitList.getListOfHabits().size() == 0) {
            System.out.println("\nChoose an option to continue with:");

            System.out.println("\t1 -> add a new habit");
            System.out.println("\tQ -> quit");
        } else {
            System.out.println("\nChoose an option to continue with:");
            System.out.println("\t1 -> add a new habit");
            System.out.println("\t2 -> record or modify progress");
            System.out.println("\t3 -> reset progress");
            System.out.println("\t4 -> print summary log");
            System.out.println("\t5 -> delete habit");
            System.out.println("\tQ -> quit");
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "1":
                addHabit();
                break;
            case "2":
                recordModifyProgress();
                break;
            case "3":
                resetProgress();
                break;
            case "4":
                printLog();
                break;
            case "5":
                deleteHabit();
                break;
            default:
                System.out.println("\nSelection not valid...");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS:  adds a new habit
    private void addHabit() {
        String habitName;
        if (habitList.getListOfHabits().size() == 0) {
            System.out.println("\nYou have not added any habit yet.");
        }
        System.out.println("Let's add a new habit to your list. Start by typing the name of your habit:");
        input = new Scanner(System.in);
        habitName = input.nextLine();
        System.out.println("What is your commitment target? (in days)");
        Habit habit = processGettingCommitmentTarget(habitName);
        System.out.printf("\nYour habit is successfully added to the habit list:"
                        + "%nHabit: %s%nTarget: %d days%nStart Date: %tA %<tB %<te, %<tY%n",
                habit.getHabitName(), habit.getCommitmentTarget(), habit.getStartDate());
    }

    //MODIFIES: this
    //EFFECTS:  records or modifies progress
    public void recordModifyProgress() {
        printListOfHabits();
        System.out.println("\nChoose the habits that you want to record/modify their progress."
                + " (Please separate the numbers with a space)"
                + "\nNote: this will modify all of the listed habits");

        String userInput1 = printOutUserSelectionFromHabitList();

        if (!isRecorded || isAnotherDay) {
            System.out.println("Choose one option:\n"
                    + "\tD -> 'done', mark your today's progress as completed\n"
                    + "\tC -> 'cancel' and return to the main menu\n");
        } else {
            System.out.println("Choose one option:\n"
                    + "\tN -> 'not' done, change your today's progress to incomplete\n"
                    + "\tC -> 'cancel' and return to the main menu\n");
        }

        input = new Scanner(System.in);
        String userInput2 = input.next();

        processRecordProgressCommand(userInput1, userInput2);

    }

    //MODIFIES: this
    //EFFECTS:  resets progress
    public void resetProgress() {
        printListOfHabits();
        System.out.println("\nChoose the habits that you want to reset their progress."
                + " (Please separate the numbers with a space)");
        String userInput1 = printOutUserSelectionFromHabitList();

        System.out.println("Are you sure you want to reset your progress?:\n"
                + "\tR -> 'reset' the progress\n"
                + "\tC -> 'cancel' and return to the main menu\n"
                + "\t(Note: all the progress including streaks will be reset to zero)");

        input = new Scanner(System.in);
        String userInput2 = input.next();
        processResetProgressCommand(userInput1, userInput2);
    }

    //MODIFIES: this
    //EFFECTS:  prints summary log for each habit selected
    public void printLog() {
        printListOfHabits();
        System.out.println("\nChoose the habits that you want to print a summary of their progress."
                + " (Please separate the numbers with a space)");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        System.out.printf("%-6s%-25s%-19s%-19s%-11s%-19s%-14s%n", "#", "Habit Name", "Current Streak",
                "Highest Streak", "Target", "Total Progress", "Days left");

        String dashes = new String(new char[113]).replace("\0", "-");
        System.out.println(dashes);
        int counter = 1;
        for (String s : userInput.split(" ")) {
            System.out.printf("%-6d", counter);
            habitList.printSummaryLog(Integer.parseInt(s) - 1);
            counter++;
        }
    }

    //MODIFIES: this
    public void deleteHabit() {
        printListOfHabits();
        System.out.println("\nChoose the habits that you want to delete from your list."
                + " (Please separate the numbers with a space)");
        String userInput1 = printOutUserSelectionFromHabitList();

        System.out.println("Are you sure you want to delete your progress?:\n"
                + "\tD -> 'delete' the progress\n"
                + "\tC -> 'cancel' and return to the main menu\n"
                + "\t(Note: all the selected habits will be permanently deleted)");

        input = new Scanner(System.in);
        String userInput2 = input.next();
        processDeleteHabitCommand(userInput1, userInput2);
    }

    //REQUIRES: habitName to not be an empty string
    //MODIFIES: this
    //EFFECTS:  adds a new habit to the list of habits
    private Habit addHabitToListOfHabits(String habitName, int commitmentTarget) {
        Habit habit;
        habit = new Habit(habitName, commitmentTarget);
        habit.setStartDate(new Date());
        habitList.addHabit(habit);
        return habit;
    }

    //EFFECTS: process getting commitment target from user input; deals with exception errors
    private Habit processGettingCommitmentTarget(String habitName) {
        Habit habit = null;
        int commitmentTarget;
        boolean done = false;
        while (!done) {
            try {
                commitmentTarget = input.nextInt();
                habit = addHabitToListOfHabits(habitName, commitmentTarget);
                done = true;
            } catch (Exception e) {
                System.out.println("Oops! It seems that you haven't typed an integer. Please type in integers only.");
                input = new Scanner(System.in);
            }
        }
        return habit;
    }

    //EFFECTS: prints list of habits
    private void printListOfHabits() {
        System.out.println("\nHere is a list of your habits:");
        for (int i = 0; i < habitList.getListOfHabits().size(); i++) {
            System.out.println("\t" + (i + 1) + " -> " + habitList.getListOfHabits().get(i).getHabitName());
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command from recordModifyProgress method
    private void processRecordProgressCommand(String command1, String command2) {
        if (command2.toLowerCase().equals("d")) {
            for (String s : command1.split(" ")) {
                Habit habit = habitList.getListOfHabits().get(Integer.parseInt(s) - 1);
                habit.increment("totalCommittedDays");
                habit.increment("currentStreak");
                habit.getHabitProgress().addDate(new Date());
                isRecorded = habit.getHabitProgress().isRecorded();
                if (habit.getHabitProgress().getDateDifference() > 0) {
                    isAnotherDay = true;
                }
            }
            System.out.println("\nYour progress has been recorded successfully!");
        } else if (command2.toLowerCase().equals("n")) {

            for (String s : command1.split(" ")) {
                proccessRecordHabitNotRecorded(s);
            }
            System.out.println("\nYour progress has been recorded successfully!");
        } else if (command2.toLowerCase().equals("c")) {
            System.out.println("\nCancelled...");
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    private void proccessRecordHabitNotRecorded(String s) {
        Habit habit = habitList.getListOfHabits().get(Integer.parseInt(s) - 1);
        if (habit.getHabitProgress().isRecorded()) {
            habit.decrement("totalCommittedDays");
            habit.decrement("currentStreak");
            if (habit.getStreaksIncreasingTogether()) {
                habit.decrement("highestStreak");
            }
            habit.getHabitProgress().removeDate();
            isRecorded = habit.getHabitProgress().isRecorded();
        }
    }

    //MODIFIES: this
    //EFFECTS: process user command from resetProgress method
    private void processResetProgressCommand(String command1, String command2) {
        if (command2.toLowerCase().equals("r")) {
            for (String s : command1.split(" ")) {
                Habit habit = habitList.getListOfHabits().get(Integer.parseInt(s) - 1);
                habit.resetProgress();
            }
            System.out.println("\nYour progress has been reset successfully!");
        } else if (command2.toLowerCase().equals("c")) {
            System.out.println("\nCancelled...");
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: process user command from resetProgress method
    private void processDeleteHabitCommand(String command1, String command2) {
        if (command2.toLowerCase().equals("d")) {
            int i = 1;
            for (String s : command1.split(" ")) {
                Habit habit = habitList.getListOfHabits().get(Integer.parseInt(s) - i);
                habitList.removeHabit(habit);
                i++;
            }
            System.out.println("\nYour selection has been deleted successfully!");
        } else if (command2.toLowerCase().equals("c")) {
            System.out.println("Cancelled...");
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    //EFFECTS: prints out user selection that will be processed, and returns userInput1
    private String printOutUserSelectionFromHabitList() {
        input = new Scanner(System.in);
        String userInput1 = input.nextLine();
        int i = 1;
        System.out.println("\nYou selected:");
        for (String s : userInput1.split(" ")) {
            System.out.print("\t" + i + ". ");
            System.out.println(habitList.getListOfHabits().get(Integer.parseInt(s) - 1).getHabitName());
            i++;
        }
        return userInput1;
    }

    //EFFECTS: sets the current streak of each habit in the list to 0 if the current streak is broken;
    //         otherwise do nothing
    private void updateCurrentStreak() {
        for (Habit habit : habitList.getListOfHabits()) {
            if (habit.getHabitProgress().isCurrentStreakBroken()) {
                habit.setCurrentStreak(0);
            }
        }
    }
}
