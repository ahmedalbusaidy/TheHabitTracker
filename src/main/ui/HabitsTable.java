package ui;

import model.Habit;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import static ui.Main.habitTrackerGUI;

public class HabitsTable extends AbstractTableModel {
    //headers for the table
    private String[] columns = new String[] {
            "#", "Habit Name", "Current Streak", "Highest Streak",
            "Target", "Total Progress", "Days left", "null"
    };

    private int habitListSize = habitTrackerGUI.getHabitList().getListOfHabits().size();
    protected Object[][] data = new Object[habitListSize][columns.length];
    private JTable table = new JTable(data, columns);

    //MODIFIES: this
    //EFFECTS:  setup habit table
    public HabitsTable(String command) {
        populateTableBody(command);
        setLastColumnHeaderTitle(command);
    }

    //MODIFIES: this
    //EFFECTS:  set the title of column 7's header
    private void setLastColumnHeaderTitle(String command) {
        switch (command) {
            case "record":
                columns[7] = "Today's Progress";
                break;
            case "reset":
                columns[7] = "Reset Progress?";
                break;
            case "delete":
                columns[7] = "Delete Habit?";
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS:  populates table body
    private void populateTableBody(String command) {
        for (int i = 0; i < habitListSize; i++) {
            Habit habit = habitTrackerGUI.getHabitList().getListOfHabits().get(i);
            data[i][0] = i + 1;
            data[i][1] = habit.getHabitName();
            data[i][2] = habit.getCurrentStreak();
            data[i][3] = habit.getHighestStreak();
            data[i][4] = habit.getCommitmentTarget();
            data[i][5] = habit.getTotalCommittedDays();
            data[i][6] = habit.getCommitmentTarget() - habit.getTotalCommittedDays();
            switch (command) {
                case "record":
                    data[i][7] = habit.getHabitProgress().isRecorded();
                    break;
                default:
                    data[i][7] = new Boolean(false);
                    break;
            }
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
        return col == 7;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
