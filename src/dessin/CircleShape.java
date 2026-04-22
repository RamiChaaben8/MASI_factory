package dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleShape implements DrawableShape {
    private final Circle node = new Circle();
    private double anchorX;
    private double anchorY;

    public CircleShape() {
        node.setFill(Color.color(0.95, 0.5, 0.15, 0.20));
        node.setStroke(Color.DARKORANGE);
        node.setStrokeWidth(2.0);
    }

    @Override
    public void begin(double x, double y) {
        anchorX = x;
        anchorY = y;
        update(x, y);
    }

    @Override
    public void update(double x, double y) {
        double minX = Math.min(anchorX, x);
        double minY = Math.min(anchorY, y);
        double width = Math.abs(x - anchorX);
        double height = Math.abs(y - anchorY);
        double size = Math.min(width, height);

        node.setCenterX(minX + size / 2.0);
        node.setCenterY(minY + size / 2.0);
        node.setRadius(size / 2.0);
    }

    @Override
    public Shape getNode() {
        return node;
    }
}

