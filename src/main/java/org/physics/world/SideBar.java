package org.physics.world;

import org.physics.library.collision.shapes.Ball;
import org.physics.library.common.Vec2;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SideBar extends JPanel implements ActionListener {
    int width, height;
    public JButton pauseButton, addBall, showHitbox;
    Random random;


    DrawingCanvas dc;

    public SideBar(int w, int h, DrawingCanvas dc) {
        width = w;
        height = h;
        random = new Random();
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

        showHitbox = new JButton("Show");
        showHitbox.setActionCommand("Show");
        showHitbox.addActionListener(this);

        bottom.add(pauseButton);
        bottom.add(addBall);
        bottom.add(showHitbox);
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
        else if(eventName.equals("Resume")) {
            World.timeAtResume = System.nanoTime();
            World.paused = false;

            System.out.println("Resume button is clicked.");
            pauseButton.setActionCommand("Pause");
            pauseButton.setText("Pause");
        }

        if(eventName.equals("Add Ball")) {
            int size = (int)(Math.random() * 26) + 25;
            int posX = (int)(Math.random() * (dc.width -  size));
            int posY = (int)(Math.random() * (dc.height -  size));

            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            dc.pool.add(new Ball(size, size, new Vec2(posX,posY), new Vec2(250, 40), color));
        }

        if(eventName.equals("Show")) {
            showHitbox.setActionCommand("Hide");
            showHitbox.setText("Hide");
        }
        else if(eventName.equals("Hide")) {
            showHitbox.setActionCommand("Show");
            showHitbox.setText("Show");
        }
    }




}
