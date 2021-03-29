package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.Main.habitTrackerGUI;


public class AddHabitWindow extends FrameSetup {
    private JPanel mainPanel;
    private JPanel formPanel;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel nameLabel;
    private JLabel targetLabel;
    private JTextField nameTF;
    private JTextField targetTF;
    String habitName;
    int commitmentTarget;

    public AddHabitWindow() throws FileNotFoundException {
        setupFrame();
        setupMainPanel();
        clickAddButton();
        this.setTitle("Add a New Habit");
        this.add(mainPanel);
        setVisible(true);
    }

    //EFFECTS:  set habitName to name
    public void setHabitName(String name) {
        habitName = name;
    }

    //EFFECTS:  set commitmentTarget to target
    public void setCommitmentTarget(int target) {
        commitmentTarget = target;
    }

    //EFFECTS:  setup mainPanel
    public void setupMainPanel() {
        mainPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        setupFormPanel();
        setupAddButton();
        setupTargetButton();
    }

    //EFFECTS:  setup formPanel
    public void setupFormPanel() {
        formPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        populateFormPanel();
        mainPanel.add(formPanel);
    }

    //EFFECTS:  setup habit name
    public void setupHabitName() {
        try {
            String name = nameTF.getText();
            if (!name.isEmpty()) {
                setHabitName(name);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter a non-empty string!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS:  setup commitment name
    public void setupCommitmentTarget() {
        try {
            int target = Integer.parseInt(targetTF.getText());

            if (target >= 0) {
                System.out.println(target);
                setCommitmentTarget(target);
            } else {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer >= 0!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer >= 0!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS:  populate formPanel
    public void populateFormPanel() {
        nameLabel = new JLabel("Habit Name: ", JLabel.TRAILING);
        formPanel.add(nameLabel);
        nameTF = new JTextField(5);
        nameLabel.setLabelFor(nameTF);
        formPanel.add(nameTF);

        targetLabel = new JLabel("Commitment Target (in days): ", JLabel.TRAILING);
        formPanel.add(targetLabel);
        targetTF = new JTextField(5);
        targetLabel.setLabelFor(targetTF);
        formPanel.add(targetTF);

    }

    //EFFECTS:  setup addButton
    public void setupAddButton() {
        addButton = new JButton("Add");
        addButton.setFocusable(false);
        mainPanel.add(addButton);
    }

    private void clickAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupCommitmentTarget();
                setupHabitName();
                habitTrackerGUI.addHabitToListOfHabits(habitName, commitmentTarget);
                String[] responses = {"Done"};
                int confirmed = JOptionPane.showOptionDialog(getContentPane(),
                        "Habit is Added Successfully!",
                        "Confirmation Message", JOptionPane.CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null, responses, responses[0]);

                if (confirmed == 0) {
                    setVisible(false);
                }
            }

        });
    }

    //EFFECTS:  setup targetButton
    public void setupTargetButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        mainPanel.add(cancelButton);
    }
}