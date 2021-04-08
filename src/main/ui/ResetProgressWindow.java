package ui;

import model.Habit;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.habitTrackerGUI;

public class ResetProgressWindow extends HabitsTable {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;
    private JButton resetButton;
    private FrameSetup frame;

    //EFFECTS:  constructs record progress window
    public ResetProgressWindow() {
        super("reset");
        frame = new FrameSetup();
        setupTable();
        setupDoneButton();
        setupResetButton();
        setupMainPanel();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup frame of Reset Progress Window
    private void setupFrame() {
        frame.add(mainPanel);
        frame.setResizable(true);
        frame.setTitle("Reset Progress");
        frame.pack();
        frame.setVisible(true);
    }

    private void setupResetButton() {
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(frame.getContentPane(),
                        "Do you want to reset all selected habits?", "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,null);
                if (input == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        boolean isChecked = (boolean) table.getValueAt(i, 7);
                        Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(i);
                        if (isChecked) {
                            habitTrackerGUI.resetProgress(habit);
                        }
                    }
                }
                frame.setVisible(false);
                table.setModel(new ResetProgressWindow());
            }
        });
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add((new JLabel("Check/Uncheck the checkbox next to the habit you want to "
                + "reset its progress")));
        mainPanel.add(new JScrollPane(table));
        mainPanel.add((new JLabel("Select an option...")));
        mainPanel.add(resetButton);
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
        table = new JTable(new HabitsTable("reset"));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }
}
