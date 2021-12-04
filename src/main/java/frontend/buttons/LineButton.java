package frontend.buttons;
import backend.model.Circle;
import backend.model.Ellipse;
import backend.model.Line;
import backend.model.Point;
import frontend.wrappers.WrappedFigure;
import frontend.wrappers.WrappedLine;
import frontend.wrappers.WrappedOval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineButton extends CustomButton{
    public LineButton(String text){
        super(text);
    }
    @Override
    public WrappedFigure createFigure(Point startPoint, Point endPoint, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        return new WrappedLine(new Line(startPoint, endPoint), gc, edgeColor, edgeWidth );
    }
}