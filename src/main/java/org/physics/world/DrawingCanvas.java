package org.physics.world;

import org.physics.library.collision.AABB;
import org.physics.library.collision.Broadphase;
import org.physics.library.collision.shapes.Ball;
import org.physics.library.pooling.BallPool;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawingCanvas extends JComponent {
    int width, height;
    Ball ball;
    FpsCounter fps;
    double dt;
    BallPool array = new BallPool();;
    public ArrayList<Ball> pool = array.getPool();

    Broadphase broadphase = new Broadphase(pool);

    public DrawingCanvas(int w, int h) {
        width = w;
        height = h;

        dt = 0.0;

        fps = new FpsCounter();
        setDimension();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHints(createRenderingHints());
        moveToOrgin(g2d);

        g2d.setColor(Color.red);


        broadphase.clearList();
        if(pool.isEmpty()) {}
        else {
            for(int i = 0; i < pool.size(); i++) {
                Ball ball = pool.get(i);
                g2d.fill(new Ellipse2D.Double(
                        ball.pos.x,
                        ball.pos.y,
                        ball.diameter,
                        ball.height));

                //Draw AABB
                g2d.setColor(new Color(0, 0, 255, 100));
                AABB aabb = ball.getAABB();
                g2d.drawRect(
                        (int)aabb.minX,
                        (int)aabb.minY,
                        (int)(aabb.maxX - aabb.minX),
                        (int)(aabb.maxY - aabb.minY)
                );
                g2d.setColor(Color.red);
            }
        }

        updatePosition();
        broadphase.init();

        fps.runFpsCounter();
    }

    private RenderingHints createRenderingHints() {

        return new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
    }

    private void moveToOrgin(Graphics2D g2d) {
        g2d.translate(0, getHeight());
        g2d.scale(1, -1);
    }


    private void updatePosition() {

        for(int i = 0; i < pool.size(); i++) {
            pool.get(i).updatePosition(dt);
        }
    }

    private void setDimension() {
        setPreferredSize(new Dimension(width, height));
    }

    public void setDeltaTime(double dt) {

        this.dt = dt;
    }

}
