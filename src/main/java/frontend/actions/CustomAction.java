package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;
/*
 * clase abstracta para modelar una acción
 * Utilizado para la implementación de undo/redo
 */
public abstract class CustomAction {
    protected CanvasState<WrappedFigure> state;

    public CustomAction(CanvasState<WrappedFigure> state) {
        this.state = state;
    }

    public abstract void undo();
    public abstract void redo();
}
