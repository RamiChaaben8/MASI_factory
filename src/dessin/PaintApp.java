package dessin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaintApp extends Application {
    private final Pane drawingPane = new Pane();
    private final Label statusLabel = new Label("Choose a shape, then drag on the canvas.");
    private ShapeFactory currentFactory = new RectangleShapeFactory();
    private DrawableShape currentShape;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setTop(createToolbar());
        root.setCenter(createCanvas());
        root.setBottom(createStatusBar());

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Factory Method Paint Program");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createToolbar() {
        ToggleGroup group = new ToggleGroup();

        ToggleButton rectangleButton = createShapeButton("Rectangle", group, ShapeType.RECTANGLE, true);
        ToggleButton circleButton = createShapeButton("Cercle", group, ShapeType.CERCLE, false);
        ToggleButton lineButton = createShapeButton("Line", group, ShapeType.LINE, false);

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event -> {
            drawingPane.getChildren().clear();
            currentShape = null;
            statusLabel.setText("Canvas cleared. Select a shape and drag to draw.");
        });

        HBox toolbar = new HBox(10, rectangleButton, circleButton, lineButton, clearButton);
        toolbar.setPadding(new Insets(12));
        toolbar.setAlignment(Pos.CENTER_LEFT);
        toolbar.setStyle("-fx-background-color: #ececec; -fx-border-color: #cccccc; -fx-border-width: 0 0 1 0;");
        return toolbar;
    }

    private ToggleButton createShapeButton(String text, ToggleGroup group, ShapeType type, boolean selected) {
        ToggleButton button = new ToggleButton(text);
        button.setToggleGroup(group);
        button.setSelected(selected);
        button.setOnAction(event -> currentFactory = factoryFor(type));

        if (selected) {
            currentFactory = factoryFor(type);
        }
        return button;
    }

    private Pane createCanvas() {
        drawingPane.setPrefSize(900, 600);
        drawingPane.setStyle("-fx-background-color: white; -fx-border-color: #666666; -fx-border-width: 1;");
        drawingPane.setOnMousePressed(this::handleMousePressed);
        drawingPane.setOnMouseDragged(this::handleMouseDragged);
        drawingPane.setOnMouseReleased(this::handleMouseReleased);
        return drawingPane;
    }

    private HBox createStatusBar() {
        HBox statusBar = new HBox(statusLabel);
        statusBar.setPadding(new Insets(10, 12, 12, 12));
        statusBar.setStyle("-fx-background-color: #f8f8f8;");
        return statusBar;
    }

    private void handleMousePressed(MouseEvent event) {
        currentShape = currentFactory.creerForme();
        currentShape.begin(event.getX(), event.getY());
        drawingPane.getChildren().add(currentShape.getNode());
        statusLabel.setText("Dragging " + selectedShapeName() + "...");
    }

    private void handleMouseDragged(MouseEvent event) {
        if (currentShape != null) {
            currentShape.update(event.getX(), event.getY());
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (currentShape != null) {
            currentShape.update(event.getX(), event.getY());
            statusLabel.setText("Added " + selectedShapeName() + ".");
            currentShape = null;
        }
    }

    private String selectedShapeName() {
        if (currentFactory instanceof RectangleShapeFactory) {
            return "rectangle";
        }
        if (currentFactory instanceof CircleShapeFactory) {
            return "cercle";
        }
        return "line";
    }

    private ShapeFactory factoryFor(ShapeType type) {
        return switch (type) {
            case RECTANGLE -> new RectangleShapeFactory();
            case CERCLE -> new CircleShapeFactory();
            case LINE -> new LineShapeFactory();
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}

