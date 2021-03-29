package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;

import static ui.Main.habitTrackerGUI;

public class PrintSummaryWindow extends FrameSetup {
    private JPanel mainPanel;
    private JLabel habitLabel;
    private JPanel habitsPanel;

    //EFFECTS: set up summary log window frame
    public PrintSummaryWindow() {
        setupFrame();
        setupMainPanel();
        this.setTitle("Print Summary Log");
        this.add(mainPanel);
        this.setSize(SCREEN_HEIGHT * 2, SCREEN_HEIGHT);
        setVisible(true);
    }

    //EFFECTS: set up main screen panel
    public void setupMainPanel() {
        mainPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupHabitPanel();
        setupHabitLabel();
    }

    //EFFECTS: set up Habit label
    private void setupHabitLabel() {
        for (Habit habit : habitTrackerGUI.getHabitList().getListOfHabits()) {
            String habitSummary = habit.toString();
            habitLabel = new JLabel(habitSummary);
            habitsPanel.add(habitLabel);
        }
        mainPanel.add(habitsPanel);
    }

    //EFFECTS: set up Habit panel
    private void setupHabitPanel() {
        habitsPanel = new JPanel(new GridLayout(2, 1, 0, 5));
    }


}
