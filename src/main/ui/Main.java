package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Main extends FrameSetup {
    private JButton addNewHabitButton;
    private JButton recordOrModifyProgressButton;
    private JButton resetProgressButton;
    private JButton printSummaryLogButton;
    private JButton deleteHabitButton;
    private JButton saveProgressButton;
    private JButton loadProgressButton;

    private JLabel welcomeMessageLabel;
    private JTextArea appInfoText;

    private JPanel mainScreenPanel;
    private JPanel appIntroPanel;
    protected static HabitTrackerGUI habitTrackerGUI;

    static {
        try {
            habitTrackerGUI = new HabitTrackerGUI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Main() throws FileNotFoundException {
        super();
        setupFrame();
        setupMainScreenPanel();

        this.setTitle("The Habit Tracker");
        //exit from application
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainScreenPanel);
        this.setVisible(true);
    }

    //EFFECTS: set up main screen panel with welcome text and option buttons
    private void setupMainScreenPanel() {
        mainScreenPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        mainScreenPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupAppIntroPanel();
        setupMainScreenButtons();
    }

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
        loadProgressButton = new JButton("Load Progress");
        loadProgressButton.setFocusable(false);
        mainScreenPanel.add(loadProgressButton);
        loadProgressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitTrackerGUI.loadHabitsList();
                String[] responses = {"Done"};
                JOptionPane.showOptionDialog(getContentPane(),
                        "Your progress has been loaded successfully!",
                        "Confirmation Message", JOptionPane.CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
            }
        });
    }

    //EFFECTS:  setup saveProgressButton
    private void setupSaveProgressButton() {
        saveProgressButton = new JButton("Save Progress");
        saveProgressButton.setFocusable(false);
        mainScreenPanel.add(saveProgressButton);
        saveProgressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitTrackerGUI.saveHabitsList();
                String[] responses = {"Done"};
                JOptionPane.showOptionDialog(getContentPane(),
                        "Your progress has been saved successfully!",
                        "Confirmation Message", JOptionPane.CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
            }
        });
    }

    //EFFECTS:  setup deleteHabitButton
    private void setupDeleteHabitButton() {
        deleteHabitButton = new JButton("Delete Progress");
        deleteHabitButton.setFocusable(false);
        mainScreenPanel.add(deleteHabitButton);
    }

    //EFFECTS:  setup printSummaryLogButton
    private void setupPrintSummaryLogButton() {
        printSummaryLogButton = new JButton("Print Summary Log");
        printSummaryLogButton.setFocusable(false);
        mainScreenPanel.add(printSummaryLogButton);
        printSummaryLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new PrintSummaryWindow();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });
    }

    //EFFECTS:  setup resetProgressButton
    private void setupResetProgressButton() {
        resetProgressButton = new JButton("Reset Progress");
        resetProgressButton.setFocusable(false);
        mainScreenPanel.add(resetProgressButton);
    }

    //EFFECTS:  setup recordOrModifyProgressButton
    private void setupRecordOrModifyProgressButton() {
        recordOrModifyProgressButton = new JButton("Record or Modify Progress");
        recordOrModifyProgressButton.setFocusable(false);
        mainScreenPanel.add(recordOrModifyProgressButton);
    }

    //EFFECTS:  setup addNewHabitButton
    private void setupAddNewHabitButton() {
        addNewHabitButton = new JButton("Add New Habit");
        addNewHabitButton.setFocusable(false);
        mainScreenPanel.add(addNewHabitButton);
        addNewHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddHabitWindow();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(null, "File Not Found!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //EFFECTS:  setup app intro panel, which includes welcome message and brief intro of the app,
    //          and adds it to mainScreenPanel
    private void setupAppIntroPanel() {
        appIntroPanel = new JPanel(new BorderLayout());
        welcomeMessageLabel = new JLabel("Welcome: " + System.getProperty("user.name"));

        setupAppInfoText();
        appIntroPanel.add(welcomeMessageLabel, BorderLayout.NORTH);
        appIntroPanel.add(appInfoText, BorderLayout.SOUTH);
        mainScreenPanel.add(appIntroPanel);
    }

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

    public static void main(String[] args) {
        try {
            new Main();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
