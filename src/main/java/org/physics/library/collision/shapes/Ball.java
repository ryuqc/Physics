/*
    Ball Class
 */

package org.physics.library.collision.shapes;

import org.physics.library.collision.AABB;
import org.physics.library.common.Vec2;
import org.physics.world.World;

import java.awt.*;

public class Ball {

    public double diameter;
    public double height;
    public Vec2 pos;
    public Vec2 velocity;
    public double mass;
    Vec2 gravity;
    AABB aabb;
    public static int id = 0;
    Color color;
    private int ballID;

    public Ball(double d, double h, Vec2 p, Vec2 v, Color c) {
        id++;
        diameter = d;
        height = h;
        pos = p;
        velocity = v;
        color = c;
        mass = Math.PI * getRadius() * getRadius();

        aabb = new AABB(0, diameter, 0, h);
        gravity = new Vec2(0, 0);

        ballID = id;
    }

    /*
        Implements Kinematic Equations:
            v = a * t
            x = v * t
     */
    public void updatePosition(double dt) {
        velocity.x += gravity.x * dt;
        velocity.y += gravity.y * dt;

        pos.y += velocity.y * dt;
        pos.x += velocity.x * dt;

        aabb.setTo(pos.x, pos.y, diameter, height);

        /*
            Handles Border Collision.
         */
        if(pos.x + diameter >= World.width) {
            velocity.x *= -1;
            pos.x = World.width - diameter;
        }
        if(pos.x < 0) {
            velocity.x *= -1;
            pos.x = 0;
        }
        if(pos.y < 0) {
            velocity.y *= -1;
            pos.y = 0;
        }
        if(pos.y + diameter >= World.height) {
            velocity.y *= -1;
            pos.y = World.height - diameter;
        }
    }

    public Color getColor() {
        return color;
    }

    public Vec2 getCenter() {
        double centerX = pos.x + (diameter/2);
        double centerY = pos.y + (height/2);

        return new Vec2(centerX, centerY);
    }

    public double getRadius() {
        return diameter/2;
    }

    public AABB getAABB() {
        return aabb;
    }

    public String toString() {
        return "Ball " + ballID;
    }
}
