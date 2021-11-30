package frontend.wrappers;

import backend.model.Figure;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class WrappedRectangle extends WrappedFigure{
    public WrappedRectangle(Figure fig, GraphicsContext gc) {
        super(fig, gc);
    }

    @Override
    public void draw() {
        gc.fillRect(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
        gc.strokeRect(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
    }
}
