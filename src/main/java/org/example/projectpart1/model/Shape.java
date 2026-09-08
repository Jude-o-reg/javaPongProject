package org.example.projectpart1.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape {
    void draw(GraphicsContext gc, Color color, double x, double y, double width, double height);
}
