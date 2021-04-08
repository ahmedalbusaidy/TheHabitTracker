package ui;

import javax.swing.*;
import java.awt.*;

//This class setup the main frame
public class FrameSetup extends JFrame {
    public static final int SCREEN_WIDTH = 550;
    public static final int SCREEN_HEIGHT = 820;

    //EFFECTS:  set up frame
    public FrameSetup() {
        //sets title of frame
        setTitle("The Habit Tracker");
        //prevent frame from being resized
        setResizable(false);
        //sets the x-dimension, and y-dimension of frame
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        //change color of background
        getContentPane().setBackground(new Color(0xFFFFFF));
    }
}
