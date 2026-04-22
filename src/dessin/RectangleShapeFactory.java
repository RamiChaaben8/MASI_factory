package dessin;

public class RectangleShapeFactory extends ShapeFactory {
    @Override
    public DrawableShape creerForme() {
        return new RectangleShape();
    }
}

