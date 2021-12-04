package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;

public abstract class CustomAction {
    protected CanvasState<WrappedFigure> state;

    public CustomAction(CanvasState<WrappedFigure> state) {
        this.state = state;
    }

    public abstract void undo();
    public abstract void redo();
}
