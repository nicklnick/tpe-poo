package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public abstract class WrappedFigure implements Drawable{
    protected GraphicsContext gc;
    protected Figure fig;

    public WrappedFigure(Figure fig, GraphicsContext gc){
        this.gc = gc;
        this.fig = fig;
    }

    public Figure getFigure(){
        return fig;
    }

    public abstract void draw();

    @Override
    public String toString(){
        return fig.toString();
    }
}
