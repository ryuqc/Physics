package org.physics.world;

import javax.swing.*;
import java.awt.*;


public class World implements Runnable {
    int width = 1000, height = 500;
    final int FPS_SET = 60;

    Window window;
    DrawingCanvas dc;

    Thread t;

    public World() {
        dc = new DrawingCanvas(width, height);
        window = new Window(dc);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastChecked = 0;

        while(true) {
            if(System.nanoTime() - lastChecked >= timePerFrame) {
                lastChecked = System.nanoTime();
                dc.repaint();
            }
        }
    }
}
