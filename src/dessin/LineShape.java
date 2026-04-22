package dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineShape implements DrawableShape {
    private final Line node = new Line();
    private double startX;
    private double startY;

    public LineShape() {
        node.setStroke(Color.FORESTGREEN);
        node.setStrokeWidth(2.5);
    }

    @Override
    public void begin(double x, double y) {
        startX = x;
        startY = y;
        update(x, y);
    }

    @Override
    public void update(double x, double y) {
        node.setStartX(startX);
        node.setStartY(startY);
        node.setEndX(x);
        node.setEndY(y);
    }

    @Override
    public Shape getNode() {
        return node;
    }
}

