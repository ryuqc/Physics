package org.physics.world;


import javax.swing.*;

public class World implements Runnable {
    public static int width = 800, height = 500;
    public static final int SIDE_BAR_WIDTH = 250, SIDE_BAR_HEIGHT = 500;

    final double FPS_SET = 60;
    public static boolean paused = false;
    public static long timeAtPaused = 0;
    public static long timeAtResume = 0;

    public static boolean temp = false;



    Window window;
    DrawingCanvas dc;
    SideBar sideBar;


    Thread t;

    public World() {
        dc = new DrawingCanvas(width, height);
        sideBar = new SideBar(SIDE_BAR_WIDTH, SIDE_BAR_HEIGHT, dc);
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
            if(!World.paused) {
                currentTime = System.nanoTime();

                if (timeAtResume != 0) {
                    lastChecked += timeAtResume - timeAtPaused;
                    timeAtPaused = 0;
                    timeAtResume = 0;
                }

                if (currentTime - lastChecked >= timePerFrame) {

                    //Waits for second frame...
                    if (currentTime - lastChecked != currentTime) {

                        //Pass in a more accurate time of each frame.
                        dc.setDeltaTime((currentTime - lastChecked) / 1000000000.0);

                        dc.repaint();
                    }
                    lastChecked = currentTime;
                }
            }

            if(temp) {
                temp = false;

                System.out.println("Ordered List: " + dc.broadphase.orderedList);
                System.out.println("Active List: " + dc.broadphase.activeList);

            }

            try {
                Thread.sleep(0);
            } catch (Exception e) {}

        }


    }
}
