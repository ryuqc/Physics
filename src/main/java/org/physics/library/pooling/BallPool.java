/*
    Manages list of active objects in the window
 */

package org.physics.library.pooling;

import org.physics.library.collision.shapes.Ball;
import java.util.ArrayList;

public class BallPool {
    public ArrayList<Ball> pool;

    public BallPool() {
        pool = new ArrayList<Ball>();
    }

    public ArrayList<Ball> getPool() {
        return pool;
    }
}
