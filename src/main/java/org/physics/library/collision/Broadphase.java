package org.physics.library.collision;

import org.physics.library.collision.shapes.Ball;
import org.physics.world.DrawingCanvas;

import java.util.*;

//Sweet and Prune method
public class Broadphase {
    public ArrayList<ArrayList<Ball>> activeList = new ArrayList<>();
    public ArrayList<Ball> orderedList = new ArrayList<>();
    ArrayList<Ball> pool;

    public Broadphase(ArrayList<Ball> pool) {
        this.pool = pool;
        init();
    }

    public void init() {
        initOrderedList();
        initActiveList();
    }

    private void initOrderedList() {
        if(pool != null) {
            for(int i = 0; i < pool.size(); i++) {
                Ball ball = pool.get(i);
                Ball ballWithSmallerX = ball;

                for(int j = i + 1; j < pool.size() - 1; j ++) {
                    ballWithSmallerX = ball.smallerX(pool.get(j));
                }
                orderedList.add(ballWithSmallerX);
            }
        }
    }

    private void initActiveList() {
        for(int i = 0; i < orderedList.size() - 1; i++) {
            ArrayList<Ball> temp = new ArrayList<>();
            Ball b = orderedList.get(i);

            Ball nextB = orderedList.get(i + 1);

            temp.add(b);

            if(AABB.overlapsAABB(b.getAABB(), nextB.getAABB())) {
                temp.add(nextB);
            }
            else {
                if(temp.size() > 1) {
                    activeList.add(temp);
                }

                temp = null;
            }
        }
    }

    public void clearList() {
        orderedList.clear();
        activeList.clear();
    }
}
