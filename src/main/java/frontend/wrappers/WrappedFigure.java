package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class WrappedFigure implements Drawable{

    private static final int INIT_ID = 1;
    private static int currentId = INIT_ID;

    protected final GraphicsContext gc;
    protected final Figure fig;

    private final int id;
    private Color edgeColor;
    private Color fillColor;
    private double edgeWidth;

    public WrappedFigure(Figure fig, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth){
        this.gc = gc;
        this.fig = fig;
        this.edgeWidth = edgeWidth;
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
        id = currentId++;
    }

    public abstract void draw();

    @Override
    public String toString(){
        return fig.toString();
    }


    /* GETTERS */
    public int getId() {
        return id;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public double getEdgeWidth() {
        return edgeWidth;
    }

    public Figure getFigure(){
        return fig;
    }


    /* SETTERS */
    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public void setEdgeWidth(double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}

