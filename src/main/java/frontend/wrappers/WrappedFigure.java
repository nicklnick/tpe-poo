package frontend.wrappers;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class WrappedFigure implements Drawable{
    protected GraphicsContext gc;
    protected Figure fig;

    private Color edgeColor;
    private Color fillColor;
    private double edgeWidth;

    public WrappedFigure(Figure fig, GraphicsContext gc, Color edgeColor, Color fillColor, double edgeWidth){
        this.gc = gc;
        this.fig = fig;
        this.edgeWidth = edgeWidth;
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public void setEdgeWidth(double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
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

    public abstract void draw();

    @Override
    public String toString(){
        return fig.toString();
    }
}

