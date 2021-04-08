package ui;

import javax.swing.*;

import java.awt.*;

import static ui.FrameSetup.SCREEN_HEIGHT;

public class PrintSummaryWindow extends HabitsTable {
    private JTable table;
    private FrameSetup frame;

    //EFFECTS: set up summary log window frame
    public PrintSummaryWindow() {
        super("print");
        frame = new FrameSetup();
        setupTable();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup frame of Print Summary Window
    private void setupFrame() {
        frame.add(new JScrollPane(table)); //add the table to the frame
        frame.setTitle("Print Summary Log");
        frame.setSize(SCREEN_HEIGHT, SCREEN_HEIGHT);
        frame.setVisible(true);
    }

    //EFFECTS:  setup habit table
    private void setupTable() {
        table = new JTable(new HabitsTable("print"));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.removeColumn(table.getColumnModel().getColumn(7));
    }


}
