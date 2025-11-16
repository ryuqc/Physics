package org.physics.world;

import org.physics.library.collision.shapes.Ball;
import org.physics.library.dynamics.Position;
import org.physics.library.dynamics.Velocity;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawingCanvas extends JComponent {
    int width, height;
    Ball ball;
    FpsCounter fps;

    public DrawingCanvas(int w, int h) {
        width = w;
        height = h;

        ball = new Ball(25, 25, new Position(0,0), new Velocity(75, 75));
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
        g2d.fill(createBall());

        updatePosition();

        //System.out.println("Ball POS: " + ball.pos.x + ", " + ball.pos.y);
        fps.runFpsCounter();
    }

    private RenderingHints createRenderingHints() {
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        return rh;
    }

    private void moveToOrgin(Graphics2D g2d) {
        g2d.translate(0, getHeight());
        g2d.scale(1, -1);
    }

    public Ellipse2D createBall() {
        return new Ellipse2D.Double(ball.pos.x, ball.pos.y, ball.diameter, ball.height);
    }

    private void updatePosition() {
        ball.updatePosition();
    }

    private void setDimension() {
        this.setPreferredSize(new Dimension(width, height));
    }
}
