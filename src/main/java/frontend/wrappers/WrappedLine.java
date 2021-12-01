package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WrappedLine extends WrappedFigure{
    public WrappedLine(Figure fig, GraphicsContext gc, Color edgeColor, double edgeWidth) {
        super(fig, gc, edgeColor, null, edgeWidth);
    }

    @Override
    public void draw() {
        gc.strokeLine(fig.getX(), fig.getY(),fig.getX() - fig.getWidth(), fig.getY() - fig.getHeight());
    }
}
