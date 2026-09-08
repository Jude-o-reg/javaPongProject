package org.example.projectpart1.model;

public class ShapeFactory {
    public static Shape createShape(String type){
        return switch(type.toLowerCase()){
            case "circle" -> new CircleShape();
            case "rectangle" -> new RectangleShape();
            default -> throw new IllegalArgumentException("Unknown Type");
        };
    }
}
