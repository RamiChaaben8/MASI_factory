package dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleShape implements DrawableShape {
    private final Rectangle node = new Rectangle();
    private double anchorX;
    private double anchorY;

    public RectangleShape() {
        node.setFill(Color.color(0.2, 0.5, 0.9, 0.20));
        node.setStroke(Color.DODGERBLUE);
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

        node.setX(minX);
        node.setY(minY);
        node.setWidth(Math.abs(x - anchorX));
        node.setHeight(Math.abs(y - anchorY));
    }

    @Override
    public Shape getNode() {
        return node;
    }
}

