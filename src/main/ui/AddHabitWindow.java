package ui;

import javax.swing.*;
import java.awt.*;

import static ui.Main.habitTrackerGUI;

//This class sets up add habit window
public class AddHabitWindow {
    private JPanel mainPanel;
    private JPanel formPanel;
    private JButton addButton;
    private JTextField nameTF;
    private JTextField targetTF;
    private FrameSetup frame;
    String habitName;
    int commitmentTarget;

    //EFFECTS: set add habit window frame
    public AddHabitWindow() {
        frame = new FrameSetup();
        setupMainPanel();
        clickAddButton();
        setupFrame();
    }

    //MODIFIES: this
    //EFFECTS:  setup frame for add habit window
    private void setupFrame() {
        frame.setTitle("Add a New Habit");
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS:  set habitName to name
    public void setHabitName(String name) {
        habitName = name;
    }

    //MODIFIES: this
    //EFFECTS:  set commitmentTarget to target
    public void setCommitmentTarget(int target) {
        commitmentTarget = target;
    }

    //MODIFIES: this
    //EFFECTS:  setup mainPanel
    public void setupMainPanel() {
        mainPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupFormPanel();
        setupAddButton();
        setupTargetButton();
    }

    //MODIFIES: this
    //EFFECTS:  setup formPanel
    public void setupFormPanel() {
        formPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        populateFormPanel();
        mainPanel.add(formPanel);
    }

    //MODIFIES: this
    //EFFECTS:  setup habit name. Takes user input, validate it and then sets user input to habit name
    //          throws Exception if input is not expected, and error message is displayed
    public void setupHabitName() {
        try {
            String name = nameTF.getText();
            if (!name.isEmpty()) {
                setHabitName(name);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Please enter a non-empty string!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS:  setup commitment name. Takes user input, validate it and then sets user input to commitment target
    //          throws Exception if input is not expected (e.g negative number is inputted),
    //          and error message is displayed
    public void setupCommitmentTarget() {
        try {
            int target = Integer.parseInt(targetTF.getText());

            if (target >= 0) {
                setCommitmentTarget(target);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Please enter an integer >= 0!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS:  populate formPanel
    public void populateFormPanel() {
        JLabel nameLabel = new JLabel("Habit Name: ", JLabel.TRAILING);
        formPanel.add(nameLabel);
        nameTF = new JTextField(5);
        nameLabel.setLabelFor(nameTF);
        formPanel.add(nameTF);

        JLabel targetLabel = new JLabel("Commitment Target (in days): ", JLabel.TRAILING);
        formPanel.add(targetLabel);
        targetTF = new JTextField(5);
        targetLabel.setLabelFor(targetTF);
        formPanel.add(targetTF);

    }

    //MODIFIES: this
    //EFFECTS:  setup addButton
    public void setupAddButton() {
        addButton = new JButton("Add");
        addButton.setFocusable(false);
        mainPanel.add(addButton);
    }

    //MODIFIES: this, HabitTrackerGUI, HabitProgress, HabitList, Habit
    //EFFECTS:  when add button is clicked, creates a new habit with habit name and commitment target and adds it
    //          to the habit list. Confirmation message is displayed informing that habit is added successfully.
    private void clickAddButton() {
        addButton.addActionListener(e -> {
            setupCommitmentTarget();
            setupHabitName();
            habitTrackerGUI.addHabitToListOfHabits(habitName, commitmentTarget);
            String[] responses = {"Done"};
            Toolkit.getDefaultToolkit().beep();
            int confirmed = JOptionPane.showOptionDialog(frame.getContentPane(),
                    "Habit is Added Successfully!",
                    "Confirmation Message", JOptionPane.CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);

            if (confirmed == 0) {
                frame.setVisible(false);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS:  setup targetButton
    public void setupTargetButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        mainPanel.add(cancelButton);
    }
}
