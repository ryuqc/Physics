package org.physics.library.common;

public class Vec2 {
    public double x;
    public double y;

    public Vec2() {
        x = 0;
        y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2 v) {
        x = v.x;
        y = v.y;
    }

    public Vec2 clone() {
        return new Vec2(x, y);
    }

    public Vec2 add(Vec2 v, double s) {
        x += v.x * s;
        y += v.y * s;
        return this;
    }

    public Vec2 addVectors(Vec2 v1, Vec2 v2) {
        x = v1.x + v2.x;
        y = v1.y + v2.y;

        return this;
    }

    public Vec2 subtract(Vec2 v, double s) {
        x -= v.x * s;
        y -= v.y * s;

        return this;
    }

    public void subtractVectors(Vec2 v1, Vec2 v2) {
        x = v1.x - v2.x;
        y = v1.y - v2.y;

    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public void scale(double s) {
        x *= s;
        y *= s;
    }

    public double dot(Vec2 v) {
        return x * v.x + y * v.y;
    }

}
