package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;

import java.util.List;

public abstract class GraphicAction extends CustomAction {

    public GraphicAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state);
        savePrevious(figures);
    }

    protected abstract void savePrevious(List<WrappedFigure> figures);
}
