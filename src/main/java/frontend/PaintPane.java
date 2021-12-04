package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.actions.*;
import frontend.wrappers.WrappedLine;
import frontend.wrappers.WrappedOval;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedRect;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class PaintPane extends BorderPane {

	// BackEnd
	private CanvasState<WrappedFigure> canvasState;

	// Canvas y relacionados
	private Canvas canvas = new Canvas(800, 600);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color FILL_COLOR = Color.YELLOW;
	private static final double LINE_WIDTH = 1;
	private static final int DEF_MIN_SLIDER = 1;
	private static final int DEF_MAX_SLIDER = 50;
	private static final int DEF_INCREMENT_SLIDER = 1;

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton lineButton = new ToggleButton("Línea");
	private final Button sendToFrontButton = new Button("Al Frente");
	private final Button sendToBackButton = new Button("Al Fondo");
	private final Button deleteButton = new Button("Borrar");
	private final Text borde = new Text("Borde");
	private final Text relleno = new Text("Relleno");
	private final Button undoButton = new Button("Deshacer");
	private final Button redoButton = new Button("Rehacer");

	// FillColor Barra Izquierda
	private ColorPicker fillColorPicker = new ColorPicker(FILL_COLOR);

	// Edge Barra Izquierda
	private ColorPicker edgeColorPicker = new ColorPicker(LINE_COLOR);
	private Slider edgeWidth = new Slider(DEF_MIN_SLIDER, DEF_MAX_SLIDER, DEF_INCREMENT_SLIDER);

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private List<WrappedFigure> selectedFigures;

	// StatusBar
	private StatusPane statusPane;

	private final Stack<CustomAction> undoStack = new Stack<>();
	private final Stack<CustomAction> redoStack = new Stack<>();
	private boolean undoState = false;

	private boolean selectionMode = false;

	public PaintPane(CanvasState<WrappedFigure> canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.selectedFigures = new ArrayList<>();

		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton,
									squareButton, ellipseButton, lineButton };
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		Button[] buttonsArr = {deleteButton, sendToFrontButton, sendToBackButton, undoButton, redoButton};
		for(Button button : buttonsArr) {
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}

		edgeWidth.setShowTickMarks(true);								// Configuracion de slider
		edgeWidth.setShowTickLabels(true);
		edgeWidth.setOnMouseReleased(event -> {
			if(! selectedFigures.isEmpty()){
				CustomAction action = new WidthAction(canvasState, selectedFigures, edgeWidth.getValue());
				manageStacks(action);

				for(WrappedFigure wrappedFigure : selectedFigures)
					wrappedFigure.setEdgeWidth(edgeWidth.getValue());
				redrawCanvas();
			}
		});

		fillColorPicker.valueProperty().addListener(event -> {			// Configuracion de colorpicker1
			if(! selectedFigures.isEmpty()){
				CustomAction action = new ColorFillAction(canvasState, selectedFigures, fillColorPicker.getValue());
				manageStacks(action);

				for(WrappedFigure wrappedFigure : selectedFigures)
					wrappedFigure.setFillColor(fillColorPicker.getValue());
				redrawCanvas();
			}
		});
		edgeColorPicker.valueProperty().addListener(event -> {			// Configuracion de colorpicker2
			if(! selectedFigures.isEmpty()){
				CustomAction action = new ColorEdgeAction(canvasState, selectedFigures, edgeColorPicker.getValue());
				manageStacks(action);

				for(WrappedFigure wrappedFigure : selectedFigures)
					wrappedFigure.setEdgeColor(edgeColorPicker.getValue());
				redrawCanvas();
			}
		});


		VBox buttonsBox = new VBox(10);

		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().addAll(buttonsArr);
		buttonsBox.getChildren().addAll(borde, edgeWidth, edgeColorPicker);
		buttonsBox.getChildren().addAll(relleno, fillColorPicker);

		buttonsBox.setPadding(new Insets(5));			// Configuracion de VBOX
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		setLeft(buttonsBox);								// Posicionamiento de elementos
		setRight(canvas);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}

			WrappedFigure newFigure;

			if(lineButton.isSelected()) {
				newFigure = new WrappedLine(new Line(startPoint, endPoint), gc, edgeColorPicker.getValue(), edgeWidth.getValue());
			}
			else if( ! (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())){
				if(rectangleButton.isSelected()) {
					newFigure = new WrappedRect(new Rectangle(startPoint, endPoint), gc, edgeColorPicker.getValue(), fillColorPicker.getValue(), edgeWidth.getValue() ) ;
				}
				else if(circleButton.isSelected()) {
					double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
					newFigure = new WrappedOval(new Circle(startPoint, circleRadius), gc, edgeColorPicker.getValue(),  fillColorPicker.getValue(), edgeWidth.getValue()) ;
				} else if(squareButton.isSelected()){
					newFigure = new WrappedRect(new Square(startPoint, endPoint.getX() - startPoint.getX()), gc, edgeColorPicker.getValue(),  fillColorPicker.getValue(), LINE_WIDTH);
				} else if(ellipseButton.isSelected()) {
					newFigure = new WrappedOval(new Ellipse(startPoint, endPoint), gc, edgeColorPicker.getValue(),  fillColorPicker.getValue(), edgeWidth.getValue());
				} else if(selectionMode){
					Figure imaginaryBox = new Rectangle(startPoint, endPoint);
					for(WrappedFigure wrappedFigure : canvasState.figures()) {
						if (imaginaryBox.contains(wrappedFigure.getFigure().getFirstPoint()) && imaginaryBox.contains(wrappedFigure.getFigure().getSecondPoint())) {
							selectedFigures.add(wrappedFigure);
						}
					}
					return;
				}
				else{
					return;
				}

			}
			else {
				return ;
			}

			CustomAction action = new CreateAction(canvasState, newFigure);
			manageStacks(action);

			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(WrappedFigure wrappedFigure : canvasState.figures()) {
				if( wrappedFigure.getFigure().contains(eventPoint) ) {
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
			boolean found = false;
			StringBuilder label = new StringBuilder("Se seleccionó: ");

			if(selectionMode && !selectedFigures.isEmpty()) {				// Caso en cual se esta usando el drag para seleccionar
				for(WrappedFigure figure : selectedFigures){
					label.append(figure.toString());
				}
			}
			else if(selectionButton.isSelected()){							// Caso en cual se hace click
				Point eventPoint = new Point(event.getX(), event.getY());
				List<WrappedFigure> list = canvasState.figures();
				ListIterator<WrappedFigure> iterator = list.listIterator(list.size());			//Los elementos traceros son los que aparecen mas arriba
				while(iterator.hasPrevious() && !found){
					WrappedFigure wfig = iterator.previous();
					if (wfig.getFigure().contains(eventPoint)) {
						found = true;
						selectedFigures.clear();
						selectedFigures.add(wfig);
						label.append(wfig.toString());

						updateSliderAndColorPicker(wfig);
					}
				}
			}
			if (found || (selectionMode && !selectedFigures.isEmpty())) {	// Se hace click sobre figura
				statusPane.updateStatus(label.toString());
			} else {														// Se hace click fuera de cualquier figura
				selectedFigures.clear();
				statusPane.updateStatus("Ninguna figura encontrada");
			}
			selectionMode = false;
			redrawCanvas();
		});

		canvas.setOnMouseDragged(event -> {
			if( selectedFigures.isEmpty() && selectionButton.isSelected()){		//no hay figuras seleccionadas, se quiere seleccionar con imaginaryBox
				selectionMode = true;
				return;
			}
			Point eventPoint = new Point(event.getX(), event.getY());
			double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
			double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
			for(WrappedFigure figure : selectedFigures) {
				figure.getFigure().move(diffX, diffY);
			}
			redrawCanvas();
		});

		sendToBackButton.setOnAction(event -> {
			if(!selectedFigures.isEmpty()){

				CustomAction action = new SendToBackAction(canvasState, selectedFigures);
				sendToBack(selectedFigures);
				redrawCanvas();

				manageStacks(action);
			}
		});
		sendToFrontButton.setOnAction(event -> {
			if(!selectedFigures.isEmpty()){

				CustomAction action = new SendToFrontAction(canvasState, selectedFigures);
				sendToFront(selectedFigures);
				redrawCanvas();

				manageStacks(action);
			}
		});

		deleteButton.setOnAction(event -> {
			if( !selectedFigures.isEmpty()){
				CustomAction action = new DeleteAction(canvasState, selectedFigures);
				manageStacks(action);

				canvasState.figures().removeAll(selectedFigures);
				redrawCanvas();


			}
		});

		undoButton.setOnAction(event -> {
			if(undoStack.isEmpty()){
				statusPane.updateStatus("Nothing to undo!");
			}else {
				undoState = true;
				CustomAction action = undoStack.pop();
				redoStack.push(action);
				action.undo();
				redrawCanvas();
			}
		});

		redoButton.setOnAction(event ->{
			if( !redoStack.isEmpty() ){
				CustomAction action = redoStack.pop();
				undoStack.push(action);
				action.redo();
				redrawCanvas();
			}
			else{
				statusPane.updateStatus("Nothing to redo!");
			}
		});
	}

	private void updateSliderAndColorPicker(WrappedFigure selectedFigure){
		edgeWidth.setValue(selectedFigure.getEdgeWidth());			// Update data with shapes values
		edgeColorPicker.setValue(selectedFigure.getEdgeColor());
		fillColorPicker.setValue(selectedFigure.getFillColor());
	}

	private void sendToFront(List<WrappedFigure> selectedFigures) {
		canvasState.figures().removeAll(selectedFigures);
		canvasState.figures().addAll(selectedFigures);
	}

	private void sendToBack(List<WrappedFigure> selectedFigures) {
		canvasState.figures().removeAll(selectedFigures);
		canvasState.figures().addAll(0, selectedFigures);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(WrappedFigure wrappedFigure : canvasState.figures()) {
			if(selectedFigures.contains(wrappedFigure)) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(wrappedFigure.getEdgeColor());
			}
			gc.setLineWidth(wrappedFigure.getEdgeWidth());
			gc.setFill(wrappedFigure.getFillColor());
			wrappedFigure.draw();
		}
	}

	private void manageStacks(CustomAction action){
		undoStack.push(action);
		if (undoState){
			undoState = false;
			redoStack.clear();
		}
	}
}
