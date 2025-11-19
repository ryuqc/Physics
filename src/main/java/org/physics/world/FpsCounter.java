package org.physics.world;

public class FpsCounter {
    int frames;
    long lastChecked;

    public FpsCounter() {
        frames = 0;
        lastChecked = 0;
    }

    public void runFpsCounter() {
        frames ++;

        if(System.nanoTime() - lastChecked >= 1000000000) {
            lastChecked = System.nanoTime();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }

    public int getFPS() {
        return frames;
    }
}
