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
        orderedList.clear();

        ArrayList<Ball> tempPool = new ArrayList<>(pool);
        while(!tempPool.isEmpty()) {
            Ball min = tempPool.get(0);
            for (int i = 1; i < tempPool.size(); i++) {
                if (tempPool.get(i).pos.x < min.pos.x) {
                    min = tempPool.get(i);
                }
            }
            orderedList.add(min);
            tempPool.remove(min);
        }
    }

    //maybe broken
    private void initActiveList() {
        activeList.clear();
        for (int i = 0; i < orderedList.size(); i++) {
            Ball a = orderedList.get(i);
            ArrayList<Ball> group = new ArrayList<>();
            group.add(a);

            for (int j = i + 1; j < orderedList.size(); j++) {

                Ball b = orderedList.get(j);
                if (b.getAABB().minX > a.getAABB().maxX) {
                    break;
                }
                if (AABB.overlapsAABB(a.getAABB(), b.getAABB())) {
                    group.add(b);
                }
            }

            if (group.size() > 1) {
                activeList.add(group);
            }
        }
    }

    public ArrayList<ArrayList<Ball>> getActiveList() {
        return activeList;
    }

}

