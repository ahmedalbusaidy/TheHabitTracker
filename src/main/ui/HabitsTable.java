package ui;

import model.Habit;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import static ui.Main.habitTrackerGUI;

//This class sets up habit table model
public class HabitsTable extends AbstractTableModel {
    //headers for the table
    private String[] columns = new String[]{
            "#", "Habit Name", "Current Streak", "Highest Streak",
            "Target", "Total Progress", "Days left", "null"
    };
    private int habitListSize = habitTrackerGUI.getHabitList().getListOfHabits().size();
    protected Object[][] data = new Object[habitListSize][columns.length];
    private JTable table = new JTable(data, columns);

    //EFFECTS:  setup habit table
    public HabitsTable(String command) {
        populateTableBody(command);
        setLastColumnHeaderTitle(command);
    }

    //EFFECTS:  returns column count
    public int getColumnCount() {
        return columns.length;
    }

    //EFFECTS:  returns row count
    public int getRowCount() {
        return data.length;
    }

    //EFFECTS:  returns column name
    public String getColumnName(int col) {
        return columns[col];
    }

    //EFFECTS:  returns cell value at given row and col
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    //EFFECTS:  change default renderer so that boolean is displayed as checkbox rather than string ("true"/"false")
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //EFFECTS:  sets cells at column 7 to be editable
    public boolean isCellEditable(int row, int col) {
        return col == 7;
    }

    //EFFECTS:  sets the value of the editable cells with the value inputted by the user, i.e. column 7 checkbox is
    //          set to checked or unchecked
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
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
            if ("record".equals(command)) {
                data[i][7] = habit.getHabitProgress().isRecorded();
            } else {
                data[i][7] = new Boolean(false);
            }
        }
    }
}
