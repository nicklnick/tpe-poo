package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;

import java.util.LinkedList;
import java.util.List;


public abstract class CustomAction {
    private List<WrappedFigure> actions = new LinkedList<>();
    protected CanvasState state;

    public CustomAction(CanvasState state){
        this.state = state;
    }

    public abstract void undo();

    public abstract void redo();



}
