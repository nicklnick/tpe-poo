package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
 * En la creación de una elipse, se va a llamar a wrappedOval a pesar de
 * que elipse extienda a rectangle en el backend.
 * Esto se debe a que el gc debe dibujar la elipse como un óvalo
 * y no como un rectángulo.
 */
public class WrappedOval extends WrappedFigure {

    public WrappedOval(Figure fig, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth) {
        super(fig, gc, edgeColor, fillColor, edgeWidth);
    }
    @Override
    public void draw() {
        gc.fillOval(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
        gc.strokeOval(fig.getX(), fig.getY(),fig.getWidth(), fig.getHeight());
    }
}
