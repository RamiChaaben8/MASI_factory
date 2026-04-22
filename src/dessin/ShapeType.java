package dessin;

public enum ShapeType {
    RECTANGLE("Rectangle"),
    CERCLE("Cercle"),
    LINE("Line");

    private final String label;

    ShapeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

