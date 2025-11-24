package org.physics.world;

import org.physics.library.collision.shapes.Ball;
import org.physics.library.common.Vec2;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBar extends JPanel implements ActionListener {
    int width, height;
    public JButton pauseButton, addBall;

    DrawingCanvas dc;

    public SideBar(int w, int h, DrawingCanvas dc) {
        width = w;
        height = h;

        this.dc = dc;

        initComponents();
    }

    private void initComponents() {

        //Layout Manager
        setLayout(new BorderLayout());
        setDimension();
        configurateBorders();

        //Top Panel
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(0,1));
        top.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        top.add(new JLabel("Choose a test:"));
        top.add(new JComboBox());

        add(top, BorderLayout.NORTH);

        //Bottom Panel
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(0,1));
        bottom.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        pauseButton = new JButton("Pause");
        pauseButton.setActionCommand("Pause");
        pauseButton.addActionListener(this);

        addBall = new JButton("Add Ball");
        addBall.setActionCommand("Add Ball");
        addBall.addActionListener(this);


        bottom.add(pauseButton);
        bottom.add(addBall);
        add(bottom, "South");
    }

    private void setDimension() {
        this.setPreferredSize(new Dimension(width, height));
    }

    private void configurateBorders() {
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eventName = e.getActionCommand();

        if(eventName.equals("Pause")) {
            World.timeAtPaused = System.nanoTime();
            World.paused = true;
            World.temp = true;

            System.out.println("Pause button is clicked.");
            pauseButton.setActionCommand("Resume");
            pauseButton.setText("Resume");
        }

        if(eventName.equals("Resume")) {
            World.timeAtResume = System.nanoTime();
            World.paused = false;

            System.out.println("Resume button is clicked.");
            pauseButton.setActionCommand("Pause");
            pauseButton.setText("Pause");
        }
        if(eventName.equals("Add Ball")) {
            dc.pool.add(new Ball(25, 25, new Vec2(0.0,0.0), new Vec2(250, 40)));
        }
    }




}
