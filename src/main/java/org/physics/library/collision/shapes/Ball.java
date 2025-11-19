package org.physics.library.collision.shapes;

import org.physics.library.common.Vec2;
import org.physics.world.World;

public class Ball {
    public double diameter;
    public double height;
    public Vec2 pos;
    Vec2 velocity;
    Vec2 gravity;

    public Ball(double d, double h, Vec2 p, Vec2 v) {
        diameter = d;
        height = h;
        pos = p;
        velocity = v;

        gravity = new Vec2(0, -10);
    }

    public void updatePosition(double dt) {
        velocity.x += gravity.x * dt;
        velocity.y += gravity.y * dt;
        pos.y += velocity.y * dt;
        pos.x += velocity.x * dt;

        if(pos.x + diameter >= World.width || pos.x < 0) {
            velocity.x *= -1;
        }

        if(pos.y < 0 || pos.y + diameter >= World.height) {
            velocity.y *= -1;
        }

        //System.out.println("Velocity X Direction: " + velocity.x);
        //System.out.println("X Coordinate: " + pos.x);
    }

}
