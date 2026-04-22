package dessin;

public class LineShapeFactory extends ShapeFactory {
    @Override
    public DrawableShape creerForme() {
        return new LineShape();
    }
}

