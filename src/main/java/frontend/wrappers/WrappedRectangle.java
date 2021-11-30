package frontend.wrappers;

import backend.model.Figure;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class WrappedRectangle extends WrappedFigure{
    private Rectangle rect;
    public WrappedRectangle(Rectangle rect, GraphicsContext gc) {
        super(gc);
        this.rect = rect;
    }

    @Override
    public void draw() {
        gc.fillRect(rect.getTopLeft().getX(), rect.getTopLeft().getY(),
                Math.abs(rect.getTopLeft().getX() - rect.getBottomRight().getX()), Math.abs(rect.getTopLeft().getY() - rect.getBottomRight().getY()));
        gc.strokeRect(rect.getTopLeft().getX(), rect.getTopLeft().getY(),
                Math.abs(rect.getTopLeft().getX() - rect.getBottomRight().getX()), Math.abs(rect.getTopLeft().getY() - rect.getBottomRight().getY()));
    }
}
