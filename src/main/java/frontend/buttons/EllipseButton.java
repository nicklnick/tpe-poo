package frontend.buttons;

import backend.model.Circle;
import backend.model.Ellipse;
import backend.model.Point;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedOval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseButton extends CustomButton{
    public EllipseButton(String text){
        super(text);
    }
    @Override
    public WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        checkPoints(startPoint, endPoint);
        return new WrappedOval(new Ellipse(startPoint, endPoint), gc, edgeColor, fillColor, edgeWidth );
    }
}