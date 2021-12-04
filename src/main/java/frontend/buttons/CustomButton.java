package frontend.buttons;

import backend.model.Point;
import frontend.Exceptions.WrongDirectionException;
import frontend.wrappers.WrappedFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public abstract class CustomButton extends ToggleButton {
    public CustomButton(String text) {
        super(text);
    }

    public abstract WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth);

    protected void checkPoints(Point startPoint, Point endPoint){
        if( (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) )
            throw new WrongDirectionException();
    }
}