package dessin;

public class CircleShapeFactory extends ShapeFactory {
    @Override
    public DrawableShape creerForme() {
        return new CircleShape();
    }
}

