package org.physics.world;

import javax.swing.*;
import java.awt.*;

public class Window {
    JFrame frame;

    public Window(DrawingCanvas dc, JPanel sideBar) {

        // Set up frame
        frame = new JFrame("Test");
        frame.setUndecorated(false);

        // Frame Layout
        frame.setLayout(new BorderLayout());

        // Add components
        frame.add(dc, BorderLayout.CENTER);
        frame.add(sideBar, BorderLayout.EAST);

        // Resize then display frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//        System.out.println("Frame size: " + frame.getSize());
//        System.out.println("Canvas size: " + dc.getSize());
    }
}
