package org.physics.library.pooling;

import org.physics.library.collision.shapes.Ball;

import java.util.ArrayList;

public class BallPool {
    public ArrayList<Ball> pool;

    public BallPool() {
        pool = new ArrayList<Ball>();
    }

    public void add(Ball b) {
        pool.add(b);
    }

    public ArrayList<Ball> getPool() {
        return pool;
    }



}
