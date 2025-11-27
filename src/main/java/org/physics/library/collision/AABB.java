/*
    Hitbox Class
 */

package org.physics.library.collision;

public class AABB {
    public double minX, maxX;
    public double minY, maxY;

    public AABB(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public static boolean overlapsAABB(AABB a, AABB b) {
        return (a.maxX >= b.minX &&
                b.maxX >= a.minX);
    }

    public void setTo(double x, double y, double width, double height) {
        minX = x;
        maxX = x + width;
        minY = y;
        maxY = y + height;
    }
}
