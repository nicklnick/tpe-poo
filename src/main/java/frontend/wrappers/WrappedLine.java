package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public class WrappedLine extends WrappedFigure{
    public WrappedLine(Figure fig, GraphicsContext gc) {
        super(fig, gc);
    }

    @Override
    public void draw() {
        gc.strokeLine(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
    }
}
