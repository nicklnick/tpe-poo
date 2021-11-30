package frontend;

import backend.model.Circle;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.wrappers.WrappedOval;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedRect;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	// BackEnd
	private CanvasStateWrapped canvasState;

	// Canvas y relacionados
	private Canvas canvas = new Canvas(800, 600);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton lineButton = new ToggleButton("Línea");

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private WrappedFigure selectedFigure;

	// StatusBar
	private StatusPane statusPane;

	public PaintPane(CanvasStateWrapped canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton,
									squareButton, ellipseButton, lineButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});
		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}
			WrappedFigure newFigure;
			if(rectangleButton.isSelected()) {
				newFigure = new WrappedRect(new Rectangle(startPoint, endPoint), gc) ;
			}
			else if(circleButton.isSelected()) {
				double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new WrappedCircle(new Circle(startPoint, circleRadius), gc) ;
			} else if(squareButton.isSelected()){
				newFigure = new WrappedRectangle(new Square(startPoint, endPoint.getX() - startPoint.getX()), gc);
			} else if(ellipseButton.isSelected()) {
				newFigure = new WrappedCircle(new Ellipse(startPoint, endPoint), gc);
			}
			/*
			else if(lineButton.isSelected()) {
				newFigure = new WrappedLine(new Line(startPoint, endPoint), gc);
			}
			 */
			else {
				return ;
			}
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(WrappedFigure wrappedFigure : canvasState.figures()) {
				if( wrappedFigure.getFigure().figureBelongs(eventPoint) ) {
					found = true;
					label.append(wrappedFigure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (WrappedFigure wrappedFigure : canvasState.figures()) {
					if( wrappedFigure.getFigure().figureBelongs(eventPoint) ) {
						found = true;
						selectedFigure = wrappedFigure;
						label.append(wrappedFigure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if( selectedFigure != null)
					selectedFigure.getFigure().move(diffX, diffY);
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(WrappedFigure wrappedFigure : canvasState.figures()) {
			if(wrappedFigure == selectedFigure) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(lineColor);
			}
			gc.setFill(fillColor);
			wrappedFigure.draw();
		}
	}
}
