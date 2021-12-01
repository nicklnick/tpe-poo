package frontend.wrappers;

import backend.model.Figure;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WrappedRect extends WrappedFigure{
    public WrappedRect(Figure fig, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        super(fig, gc, edgeColor, fillColor, edgeWidth);
    }

    @Override
    public void draw() {
        gc.fillRect(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
        gc.strokeRect(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
    }
}
