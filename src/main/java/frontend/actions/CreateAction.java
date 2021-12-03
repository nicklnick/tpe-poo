package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;


public class CreateAction extends CustomAction{

    private final int position;
    private final WrappedFigure figure;

    public CreateAction(CanvasState<WrappedFigure> state, WrappedFigure figure){
        super(state);
        position = state.figures().indexOf(figure);
        this.figure = figure;
    }

    @Override
    public void undo() {
        state.figures().remove(position);
    }

    @Override
    public void redo() {
        state.figures().add(position, figure);
    }
}
