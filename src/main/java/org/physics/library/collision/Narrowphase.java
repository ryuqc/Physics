package org.physics.library.collision;

import org.physics.library.collision.shapes.Ball;
import java.util.ArrayList;

public class Narrowphase {
    Broadphase broadphase;

    public Narrowphase(Broadphase b) {
        broadphase = b;
    }

    /*
        Returns list of objects colliding.
     */
    public ArrayList<ArrayList<Ball>> touchingGroup() {
        ArrayList<ArrayList<Ball>> activeList = broadphase.getActiveList();
        ArrayList<ArrayList<Ball>> touchingGroup = new ArrayList<>();

        for(int i = 0; i < activeList.size(); i++) {

            ArrayList<Ball> group = activeList.get(i);
            ArrayList<Ball> temp = new ArrayList<>();

            for(int j = 1; j < group.size(); j++) {
                Ball mainBall = group.getFirst();
                Ball secBall = group.get(j);

                double x = secBall.getCenter().x - mainBall.getCenter().x;
                double y = secBall.getCenter().y - mainBall.getCenter().y;

                // Could use Vec2 length() method
                double sumOfR = mainBall.getRadius() + secBall.getRadius();

                if(Math.hypot(x, y) < sumOfR) {
                    temp.add(mainBall);
                    temp.add(secBall);
                }
            }
            if(!temp.isEmpty()) {
                touchingGroup.add(temp);
            }
        }
        return touchingGroup;
    }
}
