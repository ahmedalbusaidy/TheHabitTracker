package ui;

import model.Habit;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.habitTrackerGUI;

public class RecordProgressWindow extends FrameSetup {
    private JPanel mainPanel;
    private JTable table;
    private JButton doneButton;

    //EFFECTS:  constructs record progress window
    public RecordProgressWindow() {
        setupFrame();
        setupTable();
        setupDoneButton();
        setupMainPanel();

        this.add(mainPanel);
        this.setResizable(true);
        this.setTitle("Record or Modify Progress");
        this.pack();
        setVisible(true);
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
                setVisible(false);
            }
        });
    }

    private void setupTable() {
        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }


    class MyTableModel extends AbstractTableModel {
        //headers for the table
        private String[] columns = new String[] {
                "#", "Habit Name", "Current Streak", "Highest Streak",
                "Target", "Total Progress", "Days left", "Today's Progress"
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
                data[i][7] = new Boolean(habit.getHabitProgress().isRecorded());
            }
            //table = new JTable(data, columns);
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

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 7) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
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
            table.setModel(new MyTableModel());
        }
    }
}
