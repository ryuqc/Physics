package org.physics.world;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SideBar extends JPanel {
    int width, height;
    JLabel velocity, velocityX, velocityY, time;

    public SideBar(int w, int h) {
        width = w;
        height = h;

        //Labels
        velocity = new JLabel("Total_Velocity");
        velocityX = new JLabel("Velocity_X");
        velocityY = new JLabel("Velocity_Y");
        time = new JLabel("Time");

        //Layout Manager
        setLayout(new GridLayout(2, 2));

        //Dimensions
        setDimension();

        //Borders
        configurateBorders();

        //Add Components
        add(velocity);
        add(time);
        add(velocityX);
        add(velocityY);

    }



    private void setDimension() {
        this.setPreferredSize(new Dimension(width, height));
    }

    private void displayInformation() {

    }

    private void addInputFields() {

    }

    private void configurateBorders() {
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setBackground(Color.LIGHT_GRAY);
    }
}
