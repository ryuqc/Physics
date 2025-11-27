package org.physics.library.collision;

import org.physics.library.collision.shapes.Ball;
import org.physics.library.common.Vec2;

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
                Ball b1 = group.get(0);
                Ball b2 = group.get(1);

                handleBallCollision(b1, b2);
            }
        }
    }

    private void handleBallCollision (Ball b1, Ball b2) {

        Vec2 dir = new Vec2();

        //So that vector 1 is pointing at vector 2
        dir.subtractVectors(b2.getCenter(), b1.getCenter());
        double d = dir.length();

        //Normalizes vector: makes dir length 1
        dir.scale(1.0 / d);

        //to push the ball apart
        double corr = (b1.getRadius() + b2.getRadius()) / 60;
        b1.pos.add(dir, -corr);
        b2.pos.add(dir, corr);

        //prepare for formula
        double v1 = b1.velocity.dot(dir);
        double v2 = b2.velocity.dot(dir);

        double m1 = b1.mass;
        double m2 = b2.mass;

        //Formula to find new velocities
        double newV1 = (m1 * v1 + m2 * v2 - m2 * (v1 - v2)) / (m1 + m2);
        double newV2 = (m1 * v1 + m2 * v2 - m1 * (v2 - v1)) / (m1 + m2);

        b1.velocity.add(dir, newV1 - v1);
        b2.velocity.add(dir, newV2 - v2);
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
