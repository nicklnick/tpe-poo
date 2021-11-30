package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public abstract class WrappedFigure implements Drawable{
    protected GraphicsContext gc;
    public WrappedFigure(GraphicsContext gc){
        this.gc = gc;
    }
    public abstract void draw();
}
