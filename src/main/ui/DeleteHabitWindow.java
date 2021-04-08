
package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;

import static ui.Main.habitTrackerGUI;

//This class sets up delete progress window
public class DeleteHabitWindow extends HabitsTable {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;
    private JButton deleteButton;
    private FrameSetup frame;

    //EFFECTS:  constructs record progress window
    public DeleteHabitWindow() {
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

    //MODIFIES: this, HabitTrackerGUI, HabitProgress, HabitList, Habit
    //EFFECTS:  setup delete button. If delete button is clicked then:
    //              Display warning message requesting user to confirm action (Yes-No option). If "yes" is clicked then:
    //                  delete all selected habits from the habit list;
    //                  hide warning message and update table;
    //              Otherwise:
    //                  hide warning message and no other action is performed
    private void setupDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
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
            table.setModel(new DeleteHabitWindow());
        });
    }

    //MODIFIES: this
    //EFFECTS:  sets up main panel
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

    //MODIFIES: this
    //EFFECTS:  sets up done button. If done button is clicked:
    //              hides delete habit window and returns to main menu
    private void setupDoneButton() {
        doneButton = new JButton("Done");
        doneButton.addActionListener(e -> frame.setVisible(false));
    }

    //MODIFIES: this
    //EFFECTS:  setup habit table which displays all habits and their progress details
    private void setupTable() {
        table = new JTable(new HabitsTable("delete"));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }
}

