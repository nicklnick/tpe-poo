package frontend.actions;

import backend.CanvasState;
import frontend.data.ColorData;
import frontend.wrappers.WrappedFigure;
import javafx.scene.paint.Color;

import java.util.List;

public class ColorEdgeAction extends GraphicAction<ColorData> {

    private Color after;

    public ColorEdgeAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures, Color after) {
        super(state, figures);
        this.after = after;
    }

    @Override
    protected void savePrevious(List<WrappedFigure> figures) {
        for(WrappedFigure figure : figures) {
            before.add(new ColorData(figure.getId(), figure.getEdgeColor()));
        }
    }

    @Override
    public void undo() {
        for(WrappedFigure figure : state.figures()) {
            for(ColorData data : before) {
                if(figure.getId() == data.getId()) {
                    figure.setEdgeColor(data.getColor());
                }
            }
        }
    }

    @Override
    public void redo() {
        for(WrappedFigure figure : state.figures()){
            for(ColorData data : before){
                if(figure.getId() == data.getId()){
                    figure.setEdgeColor(after);
                }
            }
        }
    }
}
