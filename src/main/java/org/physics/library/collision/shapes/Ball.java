package org.physics.library.collision.shapes;

import org.physics.library.dynamics.Position;
import org.physics.library.dynamics.Velocity;

public class Ball {
    public double diameter;
    public double height;
    public Position pos;
    Velocity velocity;
    Velocity gravity;
    double dt = 1/60.0;

    public Ball(double d, double h, Position p, Velocity v) {
        diameter = d;
        height = h;
        pos = p;
        velocity = v;
        gravity = new Velocity(0, -10);
    }

    public void updatePosition() {
        velocity.x += gravity.x * dt;
        velocity.y += gravity.y * dt;

        pos.x += velocity.x * dt;
        pos.y += velocity.y * dt;
    }
}
