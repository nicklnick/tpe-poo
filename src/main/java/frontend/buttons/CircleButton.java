package frontend.buttons;

import backend.model.Circle;
import backend.model.Point;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedOval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class CircleButton extends CustomButton{

    public CircleButton(String text){
        super(text);
    }

    @Override
    public WrappedFigure createFigure(@NotNull Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        double circleRadius = startPoint.distanceTo(endPoint);
        return new WrappedOval(new Circle(startPoint, circleRadius), gc, edgeColor, fillColor, edgeWidth );
    }
}