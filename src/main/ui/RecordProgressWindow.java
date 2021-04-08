package ui;

import model.Habit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.habitTrackerGUI;

public class RecordProgressWindow extends HabitsTable {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;
    private FrameSetup frame;

    //EFFECTS:  constructs record progress window
    public RecordProgressWindow() {
        super("record");
        frame = new FrameSetup();
        setupTable();
        setupDoneButton();
        setupMainPanel();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup frame for record progress window
    private void setupFrame() {
        frame.add(mainPanel);
        frame.setResizable(true);
        frame.setTitle("Record or Modify Progress");
        frame.pack();
        frame.setVisible(true);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add((new JLabel("Check/Uncheck the checkbox next to the habit you want to "
                + "record/modify your progress")));
        mainPanel.add(new JScrollPane(table));
        mainPanel.add((new JLabel("Click 'Done' when you are over.")));
        mainPanel.add(doneButton);
    }

    private void setupDoneButton() {
        doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    private void setupTable() {
        table = new JTable(new HabitsTable("record")) {
            @Override
            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
                Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(row);
                if (value.equals(true)) {
                    habitTrackerGUI.updateHabitProgressIsCompleted(habit);
                }
                if (value.equals(false)) {
                    habitTrackerGUI.updateHabitProgressNotCompleted(habit);
                }
                table.setModel(new HabitsTable("record"));
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }
}
