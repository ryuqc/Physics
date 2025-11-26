package org.physics.library.collision;

import org.physics.library.collision.shapes.Ball;

import java.util.ArrayList;
import java.util.Vector;

public class Collision {
    Broadphase broadphase;
    Narrowphase narrowphase;

    public Collision(ArrayList<Ball> pool) {
        broadphase = new Broadphase(pool);
        narrowphase = new Narrowphase(broadphase);
    }

    public void updateBallComponents() {
        ArrayList<ArrayList<Ball>> touchingGroup = narrowphase.touchingGroup();

        if(!touchingGroup.isEmpty()) {
            for(int i= 0; i < touchingGroup.size(); i++) {
                ArrayList<Ball> group = touchingGroup.get(i);
                for(int j = i + 1; j < group.size(); j++) {
                    group.get(i).velocity.x *= -1;
                    group.get(i).velocity.y *= -1;
                }
            }
        }
    }

    public void startBroadphase() {
        broadphase.init();
    }

    public Broadphase getBroadphase() {
        return broadphase;
    }

    public ArrayList<ArrayList<Ball>> getTouchingList() {
            return narrowphase.touchingGroup();
    }


}
