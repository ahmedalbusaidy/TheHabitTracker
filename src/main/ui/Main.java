package ui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

//This class set up the main window of the habit tracker
public class Main {

    private JTextArea appInfoText;
    private JPanel mainScreenPanel;
    private FrameSetup frame;
    protected static HabitTrackerGUI habitTrackerGUI;

    static {
        habitTrackerGUI = new HabitTrackerGUI();
    }

    //EFFECTS:  constructs main screen frame and panel with buttons
    public Main() {
        frame = new FrameSetup();
        setupMainScreenPanel();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup main frame
    private void setupFrame() {
        frame.setTitle("The Habit Tracker");
        //exit from application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainScreenPanel);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS:  set up main screen panel with welcome text and option buttons
    private void setupMainScreenPanel() {
        mainScreenPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        mainScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupAppIntroPanel();
        setupMainScreenButtons();
    }

    //MODIFIES: this
    //EFFECTS:  setup main screen buttons and add them to the mainScreenPanel
    private void setupMainScreenButtons() {
        setupAddNewHabitButton();
        setupRecordOrModifyProgressButton();
        setupResetProgressButton();
        setupPrintSummaryLogButton();
        setupDeleteHabitButton();
        setupSaveProgressButton();
        setupLoadProgressButton();
    }

    //EFFECTS:  setup loadProgressButton
    private void setupLoadProgressButton() {
        JButton loadProgressButton = new JButton("Load Progress");
        loadProgressButton.setFocusable(false);
        mainScreenPanel.add(loadProgressButton);
        loadProgressButton.addActionListener(e -> {
            habitTrackerGUI.loadHabitsList();
            String[] responses = {"Done"};
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showOptionDialog(frame.getContentPane(),
                    "Your progress has been loaded successfully!",
                    "Confirmation Message", JOptionPane.CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup saveProgressButton
    private void setupSaveProgressButton() {
        JButton saveProgressButton = new JButton("Save Progress");
        saveProgressButton.setFocusable(false);
        mainScreenPanel.add(saveProgressButton);
        saveProgressButton.addActionListener(e -> {
            habitTrackerGUI.saveHabitsList();
            String[] responses = {"Done"};
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showOptionDialog(frame.getContentPane(),
                    "Your progress has been saved successfully!",
                    "Confirmation Message", JOptionPane.CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup deleteHabitButton
    private void setupDeleteHabitButton() {
        JButton deleteHabitButton = new JButton("Delete Progress");
        deleteHabitButton.setFocusable(false);
        mainScreenPanel.add(deleteHabitButton);
        deleteHabitButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            new DeleteProgressWindow();
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup printSummaryLogButton
    private void setupPrintSummaryLogButton() {
        JButton printSummaryLogButton = new JButton("Print Summary Log");
        printSummaryLogButton.setFocusable(false);
        mainScreenPanel.add(printSummaryLogButton);
        printSummaryLogButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            new PrintSummaryWindow();
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup resetProgressButton
    private void setupResetProgressButton() {
        JButton resetProgressButton = new JButton("Reset Progress");
        resetProgressButton.setFocusable(false);
        mainScreenPanel.add(resetProgressButton);
        resetProgressButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            new ResetProgressWindow();
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup recordOrModifyProgressButton
    private void setupRecordOrModifyProgressButton() {
        JButton recordOrModifyProgressButton = new JButton("Record or Modify Progress");
        recordOrModifyProgressButton.setFocusable(false);
        mainScreenPanel.add(recordOrModifyProgressButton);
        recordOrModifyProgressButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            new RecordProgressWindow();
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup addNewHabitButton
    private void setupAddNewHabitButton() {
        JButton addNewHabitButton = new JButton("Add New Habit");
        addNewHabitButton.setFocusable(false);
        mainScreenPanel.add(addNewHabitButton);
        addNewHabitButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            new AddHabitWindow();
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup app intro panel, which includes welcome message and brief intro of the app,
    //          and adds it to mainScreenPanel
    private void setupAppIntroPanel() {
        JPanel appIntroPanel = new JPanel(new BorderLayout());
        JLabel welcomeMessageLabel = new JLabel("Welcome: " + System.getProperty("user.name"));

        setupAppInfoText();
        appIntroPanel.add(welcomeMessageLabel, BorderLayout.NORTH);
        appIntroPanel.add(appInfoText, BorderLayout.SOUTH);
        mainScreenPanel.add(appIntroPanel);
    }

    //MODIFIES: this
    //EFFECTS:  setup appInfoText
    private void setupAppInfoText() {
        appInfoText = new JTextArea("Now you can track your habits easily using The Habit Tracker, where you can add "
                + "new habits to your list, set a commitment target, display your progress and much more! "
                + "Habit Tracking never been easier before!");
        appInfoText.setLineWrap(true);
        appInfoText.setWrapStyleWord(true);
        appInfoText.setOpaque(false);
        appInfoText.setEditable(false);
    }

    //EFFECTS:  runs the application
    public static void main(String[] args) {
        new Main();
    }
}
