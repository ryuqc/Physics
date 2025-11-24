package org.physics.library.common;

public class Vec2 {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dotProduct(Vec2 v1, Vec2 v2 ) {
        return v1.x * v2.x + v1.y * v2.y;
    }



    public double cos(double v) {
        return Math.cos(v);
    }

    public double sin(double v) {
        return Math.sin(v);
    }
}
