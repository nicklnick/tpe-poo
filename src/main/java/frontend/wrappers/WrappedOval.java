package frontend.wrappers;

import backend.model.Circle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public class WrappedOval extends WrappedFigure {
    public WrappedOval(Figure fig, GraphicsContext gc) {
        super(fig, gc);
    }

    @Override
    public void draw() {
        gc.fillOval(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
        gc.strokeOval(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
    }
}
