
package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.habitTrackerGUI;

public class DeleteProgressWindow extends HabitsTable {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;
    private JButton deleteButton;
    private FrameSetup frame;

    //EFFECTS:  constructs record progress window
    public DeleteProgressWindow() {
        super("delete");
        frame = new FrameSetup();
        setupTable();
        setupDoneButton();
        setupDeleteButton();
        setupMainPanel();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup frame of Delete Progress Window
    private void setupFrame() {
        frame.add(mainPanel);
        frame.setResizable(true);
        frame.setTitle("Delete Progress");
        frame.pack();
        frame.setVisible(true);
    }

    private void setupDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(frame.getContentPane(),
                        "Do you want to delete all selected habits?", "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                if (input == JOptionPane.YES_OPTION) {
                    int[] previousIndex = {0};
                    for (int i = 0; i < table.getRowCount(); i++) {
                        boolean isChecked = (boolean) table.getValueAt(i, 7);
                        Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(previousIndex[0]);
                        if (isChecked) {
                            habitTrackerGUI.deleteHabit(habit);
                        } else {
                            previousIndex[0]++;
                        }
                    }
                }
                frame.setVisible(false);
                table.setModel(new DeleteProgressWindow());
            }
        });
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add((new JLabel("Check/Uncheck the checkbox next to the habit you want to "
                + "delete its progress")));
        mainPanel.add(new JScrollPane(table));
        mainPanel.add((new JLabel("Select an option...")));
        mainPanel.add(deleteButton);
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
        table = new JTable(new HabitsTable("delete"));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }
}

