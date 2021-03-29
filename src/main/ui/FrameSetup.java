package ui;

import javax.swing.*;
import java.awt.*;

public class FrameSetup extends JFrame {

    public static final int SCREEN_WIDTH = 550;
    public static final int SCREEN_HEIGHT = 820;


    public FrameSetup() {
    }

    public void setupFrame() {
        //sets title of frame
        this.setTitle("The Habit Tracker");
        //prevent frame from being resized
        this.setResizable(false);
        //sets the x-dimension, and y-dimension of frame
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        //change color of background
        this.getContentPane().setBackground(new Color(0xFFFFFF));
    }
}
