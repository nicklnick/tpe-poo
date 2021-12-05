package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;

import java.util.LinkedList;
import java.util.List;

/*
 * clase abstracta para ahorrar código con las acciones que correspondan
 * a variables representadas gráficamente.
 */
public abstract class GraphicAction<T> extends CustomAction {

    protected final List<T> before = new LinkedList<>();

    public GraphicAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state);
        savePrevious(figures);
    }

    protected abstract void savePrevious(List<WrappedFigure> figures);
}
