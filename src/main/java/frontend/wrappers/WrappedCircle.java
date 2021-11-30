package frontend.wrappers;

import backend.model.Circle;
import javafx.scene.canvas.GraphicsContext;

public class WrappedCircle extends WrappedFigure {
    private Circle circle;
    public WrappedCircle(Circle circle, GraphicsContext gc) {
        super(gc);
        this.circle = circle;
    }

    @Override
    public void draw() {
        double diameter = circle.getRadius() * 2;
        gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
    }
}
