package frontend.buttons;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedRect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectButton extends CustomButton{
    public RectButton(String text){
        super(text);
    }
    @Override
    public WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        return new WrappedRect(new Rectangle(startPoint, endPoint), gc, edgeColor, fillColor, edgeWidth );
    }
}
