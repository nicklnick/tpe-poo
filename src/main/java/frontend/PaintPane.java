package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Exceptions.NoFigureSelectedException;
import frontend.Exceptions.HistoryStackException;
import frontend.Exceptions.WrongDirectionException;
import frontend.actions.*;
import frontend.buttons.*;
import frontend.wrappers.WrappedFigure;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PaintPane extends BorderPane {

	// BackEnd
	private final CanvasState<WrappedFigure> canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color FILL_COLOR = Color.YELLOW;
	private static final int DEF_MIN_SLIDER = 1;
	private static final int DEF_MAX_SLIDER = 50;
	private static final int DEF_INCREMENT_SLIDER = 1;

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final CustomButton rectangleButton = new RectButton("Rectángulo");
	private final CustomButton circleButton = new CircleButton("Círculo");
	private final CustomButton squareButton = new SqrButton("Cuadrado");
	private final CustomButton ellipseButton = new EllipseButton("Elipse");
	private final CustomButton lineButton = new LineButton("Línea");
	private final Button sendToFrontButton = new Button("Al Frente");
	private final Button sendToBackButton = new Button("Al Fondo");
	private final Button deleteButton = new Button("Borrar");
	private final Text borde = new Text("Borde");
	private final Text relleno = new Text("Relleno");
	private final Button undoButton = new Button("Deshacer");
	private final Button redoButton = new Button("Rehacer");

	// FillColor Barra Izquierda
	private final ColorPicker fillColorPicker = new ColorPicker(FILL_COLOR);

	// Edge Barra Izquierda
	private final ColorPicker edgeColorPicker = new ColorPicker(LINE_COLOR);
	private final Slider edgeWidth = new Slider(DEF_MIN_SLIDER, DEF_MAX_SLIDER, DEF_INCREMENT_SLIDER);

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private final List<WrappedFigure> selectedFigures;

	// StatusBar
	private final StatusPane statusPane;

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

		CustomGroup customGroup = new CustomGroup();
		CustomButton[] buttons = {rectangleButton, circleButton,
				squareButton, ellipseButton, lineButton };
		customGroup.addButton(buttons);

		VBox buttonsBox = new VBox(10);

		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().addAll(buttonsArr);
		buttonsBox.getChildren().addAll(borde, edgeWidth, edgeColorPicker);
		buttonsBox.getChildren().addAll(relleno, fillColorPicker);

		buttonsBox.setPadding(new Insets(5));			// Configuracion de VBOX
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		setLeft(buttonsBox);								// Posicionamiento de elementos
		setLeft(buttonsBox);								// Posicionamiento de elementos
		setRight(canvas);

		canvas.setOnMousePressed(this::mousePressedAction);

		canvas.setOnMouseReleased(event -> mouseReleasedAction(event, customGroup));

		canvas.setOnMouseMoved(this::mouseMovedAction);

		canvas.setOnMouseClicked(this::mouseClickedAction);

		canvas.setOnMouseDragged(this::mouseDraggedAction);

		sendToBackButton.setOnAction(event -> sendToBackButtonAction());
		sendToFrontButton.setOnAction(event -> sendToFrontButtonAction());

		deleteButton.setOnAction(event -> deleteButtonAction());

		undoButton.setOnAction(event -> undoButtonAction());

		redoButton.setOnAction(event -> redoButtonAction());

		edgeWidth.setShowTickMarks(true);								// Configuracion de slider
		edgeWidth.setShowTickLabels(true);
		edgeWidth.setOnMouseReleased(event -> edgeSliderAction());

		fillColorPicker.valueProperty().addListener(event -> fillColorAction());
		edgeColorPicker.valueProperty().addListener(event -> edgeColorAction());
	}

	/* PRIVATE METHODS */

	private void mousePressedAction(MouseEvent event){
		startPoint = new Point(event.getX(), event.getY());
	}

	private void mouseReleasedAction(MouseEvent event, CustomGroup customGroup){
		Point endPoint = new Point(event.getX(), event.getY());
		if(startPoint == null) {
			return ;
		}
		try {
			CustomButton selectedButton = customGroup.getSelectedButton();
			if (selectedButton != null) {
				WrappedFigure newFigure = selectedButton.createFigure(startPoint, endPoint, gc, edgeColorPicker.getValue(), fillColorPicker.getValue(), edgeWidth.getValue());
				if (newFigure != null) {
					CustomAction action = new CreateAction(canvasState, newFigure);
					manageStacks(action);
					canvasState.addFigure(newFigure);
				}
			}
		}catch(WrongDirectionException ex){
			statusPane.updateStatus(ex.getMessage());
		}
		if (selectionMode) {
			Figure imaginaryBox = new Rectangle(startPoint, endPoint);
			for (WrappedFigure wrappedFigure : canvasState.figures()) {
				if (imaginaryBox.contains(wrappedFigure.getFigure().getFirstPoint()) && imaginaryBox.contains(wrappedFigure.getFigure().getSecondPoint())) {
					selectedFigures.add(wrappedFigure);
				}
			}
		}

		startPoint = null;
		redrawCanvas();
	}

	private void mouseMovedAction(MouseEvent event){
		Point eventPoint = new Point(event.getX(), event.getY());
		boolean found = false;
		StringBuilder label = new StringBuilder();
		for(WrappedFigure wrappedFigure : canvasState.figures()) {
			if( wrappedFigure.getFigure().contains(eventPoint) ) {
				found = true;
				label.append(wrappedFigure);
			}
		}
		if(found) {
			statusPane.updateStatus(label.toString());
		} else {
			statusPane.updateStatus(eventPoint.toString());
		}
	}

	private void mouseClickedAction(MouseEvent event){
		boolean found = false;
		StringBuilder label = new StringBuilder("Se seleccionó: ");

		if(!selectedFigures.isEmpty()) {				// Caso en cual se esta usando el drag para seleccionar
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
					label.append(wfig);

					updateSliderAndColorPicker(wfig);
				}
			}
		}
		if (found || (selectionMode && !selectedFigures.isEmpty())) {	// Se hace click sobre figura
			statusPane.updateStatus(label.toString());
		} else {														// Se hace click fuera de cualquier figura
			selectedFigures.clear();
			if(selectionButton.isSelected())
				statusPane.updateStatus("Ninguna figura encontrada");
		}
		selectionMode = false;
		redrawCanvas();
	}

	private void mouseDraggedAction(MouseEvent event){
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
	}
	/*
	 * Punto 4 del enunciado
	 */

	private void sendToBackButtonAction(){
		try {
			checkSelection(selectedFigures);

			CustomAction action = new SendToBackAction(canvasState, selectedFigures);
			sendToBack(selectedFigures);
			redrawCanvas();

			manageStacks(action);

		} catch(NoFigureSelectedException ex){
			statusPane.updateStatus(ex.getMessage());
		}
	}

	private void sendToFrontButtonAction(){
		try {
			checkSelection(selectedFigures);

			CustomAction action = new SendToFrontAction(canvasState, selectedFigures);
			sendToFront(selectedFigures);
			redrawCanvas();

			manageStacks(action);

		} catch(NoFigureSelectedException ex){
			statusPane.updateStatus(ex.getMessage());
		}
	}
	/*
	 * Punto 2 del enunciado
	 */

	private void edgeColorAction(){
		if(! selectedFigures.isEmpty()){
			CustomAction action = new ColorEdgeAction(canvasState, selectedFigures, edgeColorPicker.getValue());
			manageStacks(action);

			for(WrappedFigure wrappedFigure : selectedFigures)
				wrappedFigure.setEdgeColor(edgeColorPicker.getValue());
			redrawCanvas();
		}
	}

	private void fillColorAction(){
		if(! selectedFigures.isEmpty()){
			CustomAction action = new ColorFillAction(canvasState, selectedFigures, fillColorPicker.getValue());
			manageStacks(action);

			for(WrappedFigure wrappedFigure : selectedFigures)
				wrappedFigure.setFillColor(fillColorPicker.getValue());
			redrawCanvas();
		}
	}

	private void edgeSliderAction(){
		if(! selectedFigures.isEmpty()){
			CustomAction action = new WidthAction(canvasState, selectedFigures, edgeWidth.getValue());
			manageStacks(action);

			for(WrappedFigure wrappedFigure : selectedFigures)
				wrappedFigure.setEdgeWidth(edgeWidth.getValue());
			redrawCanvas();
		}
	}

	/*
	 * Punto 3 del enunciado.
	 */

	private void deleteButtonAction(){
		try {
			checkSelection(selectedFigures);

			CustomAction action = new DeleteAction(canvasState, selectedFigures);
			manageStacks(action);

			canvasState.figures().removeAll(selectedFigures);
			selectedFigures.clear();
			redrawCanvas();
		}catch(NoFigureSelectedException ex){
			statusPane.updateStatus(ex.getMessage());
		}
	}
	/*
	 * Punto 5 del enunciado.
	 */

	private void redoButtonAction(){
		try{
			checkStack(redoStack, "rehacer");
			CustomAction action = redoStack.pop();
			undoStack.push(action);
			action.redo();
			redrawCanvas();
		}catch(HistoryStackException ex){
			statusPane.updateStatus(ex.getMessage());
		}
	}

	private void undoButtonAction(){
		try {
			checkStack(undoStack, "deshacer");

			undoState = true;
			CustomAction action = undoStack.pop();
			redoStack.push(action);
			action.undo();
			redrawCanvas();
		}catch(HistoryStackException ex){
			statusPane.updateStatus(ex.getMessage());
		}
	}

	/* Update data with shapes values */
	private void updateSliderAndColorPicker(@NotNull WrappedFigure selectedFigure){
		edgeWidth.setValue(selectedFigure.getEdgeWidth());
		edgeColorPicker.setValue(selectedFigure.getEdgeColor());
		fillColorPicker.setValue(selectedFigure.getFillColor());
	}

	/* Sends selected figures to front */
	private void sendToFront(List<WrappedFigure> selectedFigures) {
		canvasState.figures().removeAll(selectedFigures);
		canvasState.figures().addAll(selectedFigures);
	}

	/* Sends selected figures to back */
	private void sendToBack(List<WrappedFigure> selectedFigures) {
		canvasState.figures().removeAll(selectedFigures);
		canvasState.figures().addAll(0, selectedFigures);
	}

	private void redrawCanvas() {
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
	/*
	 * Si se deshicieron acciones, cuando se crea una nueva accion mientras estas no se rehicieron se eliminan.
	 */

	private void manageStacks(CustomAction action){
		undoStack.push(action);
		if (undoState){
			undoState = false;
			redoStack.clear();
		}
	}

	/*
	 * manejo de errores para rehacer y deshacer y el modificado de figuras en paintpane
	 */

	private void checkSelection(@NotNull List<WrappedFigure> selectedFigures){
		if(selectedFigures.isEmpty())
			throw new NoFigureSelectedException();
	}
	private void checkStack(@NotNull Stack<CustomAction> stack, String message){
		if(stack.isEmpty())
			throw new HistoryStackException(message);
	}
}
