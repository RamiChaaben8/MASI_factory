package dessin;

import javafx.scene.shape.Shape;

public interface DrawableShape {
    void begin(double x, double y);

    void update(double x, double y);

    Shape getNode();
}

