package ui;

import model.Habit;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.habitTrackerGUI;

public class ResetProgressWindow extends FrameSetup {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;
    private JButton resetButton;

    //EFFECTS:  constructs record progress window
    public ResetProgressWindow() {
        setupFrame();
        setupTable();
        setupDoneButton();
        setupResetButton();
        setupMainPanel();

        this.add(mainPanel);
        this.setResizable(true);
        this.setTitle("Reset Progress");
        this.pack();
        setVisible(true);
    }

    private void setupResetButton() {
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(getContentPane(),
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
                    table.setModel(new ResetProgressWindow.MyTableModel());
                }
                table.setModel(new ResetProgressWindow.MyTableModel());
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
                setVisible(false);
            }
        });
    }

    private void setupTable() {
        table = new JTable(new ResetProgressWindow.MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }


    class MyTableModel extends AbstractTableModel {
        //headers for the table
        private String[] columns = new String[] {
                "#", "Habit Name", "Current Streak", "Highest Streak",
                "Target", "Total Progress", "Days left", "Reset Progress?"
        };

        private int habitListSize = habitTrackerGUI.getHabitList().getListOfHabits().size();
        private Object[][] data = new Object[habitListSize][columns.length];

        //MODIFIES: this
        //EFFECTS:  setup habit table
        public MyTableModel() {
            for (int i = 0; i < habitListSize; i++) {
                Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(i);
                data[i][0] = i + 1;
                data[i][1] = habit.getHabitName();
                data[i][2] = habit.getCurrentStreak();
                data[i][3] = habit.getHighestStreak();
                data[i][4] = habit.getCommitmentTarget();
                data[i][5] = habit.getTotalCommittedDays();
                data[i][6] = habit.getCommitmentTarget() - habit.getTotalCommittedDays();
                data[i][7] = new Boolean(false);
            }
        }

        public int getColumnCount() {
            return columns.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columns[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 7) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
