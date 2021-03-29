package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;

import static ui.Main.habitTrackerGUI;

public class PrintSummaryWindow extends FrameSetup {
    private JTable table;

    //EFFECTS: set up summary log window frame
    public PrintSummaryWindow() {
        setupFrame();
        setupTable();

        this.add(new JScrollPane(table)); //add the table to the frame
        this.setTitle("Print Summary Log");
        this.setSize(SCREEN_HEIGHT, SCREEN_HEIGHT);
        setVisible(true);
    }

    //EFFECTS:  setup habit table
    private void setupTable() {
        //headers for the table
        String[] columns = new String[] {
                "#", "Habit Name", "Current Streak", "Highest Streak", "Target", "Total Progress", "Days left"
        };

        int habitListSize = habitTrackerGUI.getHabitList().getListOfHabits().size();
        Object[][] data = new Object[habitListSize][columns.length];

        for (int i = 0; i < habitListSize; i++) {
            Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(i);
            data[i][0] = i + 1;
            data[i][1] = habit.getHabitName();
            data[i][2] = habit.getCurrentStreak();
            data[i][3] = habit.getHighestStreak();
            data[i][4] = habit.getCommitmentTarget();
            data[i][5] = habit.getTotalCommittedDays();
            data[i][6] = habit.getCommitmentTarget() - habit.getTotalCommittedDays();

        }
        table = new JTable(data, columns);
    }


}
