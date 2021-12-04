package frontend.buttons;

import backend.model.Point;
import backend.model.Square;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedRect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SqrButton extends CustomButton{
    public SqrButton(String text){
        super(text);
    }
    @Override
    public WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        return new WrappedRect(new Square(startPoint, endPoint.getX() - startPoint.getX()), gc, edgeColor, fillColor, edgeWidth );
    }
}