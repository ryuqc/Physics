package org.physics.world;


import javax.swing.*;

public class World implements Runnable {
    public static int width = 800, height = 500;
    public static final int SIDE_BAR_WIDTH = 250, SIDE_BAR_HEIGHT = 500;

    final double FPS_SET = 60;

    Window window;
    DrawingCanvas dc;
    SideBar sideBar;

    Thread t;

    public World() {
        dc = new DrawingCanvas(width, height);
        sideBar = new SideBar(SIDE_BAR_WIDTH, SIDE_BAR_HEIGHT);
        window = new Window(dc, sideBar);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastChecked = 0;
        long currentTime = 0;

        while(true) {

            currentTime = System.nanoTime();

            if(currentTime - lastChecked >= timePerFrame) {

                //Waits for second run...
                if(currentTime - lastChecked != currentTime) {

                    //Pass in a more accurate time of each frame.
                    dc.setDeltaTime((currentTime - lastChecked)  / 1000000000.0);

                    dc.repaint();
                }

                lastChecked = currentTime;
            }
        }
    }
}
