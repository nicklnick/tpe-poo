package frontend.buttons;

import backend.model.Circle;
import backend.model.Point;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedOval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleButton extends CustomButton{
    public CircleButton(String text){
        super(text);
    }
    @Override
    public WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new WrappedOval(new Circle(startPoint, circleRadius), gc, edgeColor, fillColor, edgeWidth );
    }
}