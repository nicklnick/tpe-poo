package frontend.actions;

import backend.CanvasState;
import frontend.data.EdgeData;
import frontend.wrappers.WrappedFigure;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WidthAction extends GraphicAction<EdgeData> {

    private final double after;

    public WidthAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures, double after) {
        super(state, figures);
        this.after = after;
    }

    @Override
    protected void savePrevious(@NotNull List<WrappedFigure> figures) {
        for(WrappedFigure figure : figures) {
            before.add(new EdgeData(figure.getId(), figure.getEdgeWidth()));
        }
    }

    @Override
    public void undo() {
        for(WrappedFigure figure : state.figures()) {
            for(EdgeData data : before) {
                if(figure.getId() == data.getId()) {
                    figure.setEdgeWidth(data.getWidth());
                }
            }
        }
    }

    @Override
    public void redo() {
        for(WrappedFigure figure : state.figures()){
            for(EdgeData data : before){
                if(figure.getId() == data.getId()){
                    figure.setEdgeWidth(after);
                }
            }
        }
    }
}