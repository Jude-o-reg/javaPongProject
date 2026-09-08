package org.example.projectpart1.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements Shape{
    @Override
    public void draw(GraphicsContext gc, Color color, double x, double y, double width, double height) {
        gc.setFill(color);
        gc.fillOval(x, y, 15, 15);
    }
}
