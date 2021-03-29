package ui;

import com.sun.codemodel.internal.JForEach;
import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import static ui.Main.habitTrackerGUI;

public class PrintSummaryWindow extends FrameSetup {
    private JPanel mainPanel;
    private JLabel habitLabel;
    private JPanel habitsPanel;

    public PrintSummaryWindow() throws FileNotFoundException {
        setupFrame();
        setupMainPanel();
        this.setTitle("Print Summary Log");
        this.add(mainPanel);
        setVisible(true);


    }

    public void setupMainPanel() {
        mainPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupHabitPanel();
        setupHabitLabel();
    }

    private void setupHabitLabel() {
        for (Habit habit: habitTrackerGUI.getHabitList().getListOfHabits()) {
            String habitName = habit.getHabitName();
            habitLabel = new JLabel(habitName);
            habitsPanel.add(habitLabel);
        }
        mainPanel.add(habitsPanel);
    }

    private void setupHabitPanel() {
        habitsPanel = new JPanel(new GridLayout(2, 1, 0, 5));
    }




}
