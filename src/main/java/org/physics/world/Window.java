package org.physics.world;

import javax.swing.*;

public class Window {
    JFrame frame;

    public Window(DrawingCanvas dc) {

        // Set up frame
        frame = new JFrame("Test");
        frame.setUndecorated(false);

        // add components
        frame.add(dc);

        //resize and then display the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Frame size: " + frame.getSize());
        System.out.println("Canvas size: " + dc.getSize());
    }
}
