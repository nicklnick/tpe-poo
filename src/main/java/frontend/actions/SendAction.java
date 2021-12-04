package frontend.actions;

import backend.CanvasState;
import frontend.data.PositionData;
import frontend.wrappers.WrappedFigure;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class SendAction extends CustomAction {

    protected SortedSet<PositionData> before = new TreeSet<>();
    protected SortedSet<PositionData> after = new TreeSet<>();

    public SendAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state);
        savePositions(figures);
        saveAfter();
    }

    protected abstract void saveAfter();

    @Override
    public void undo() {
        applyChanges(before);
    }

    @Override
    public void redo() {
        applyChanges(after);
    }

    /* PRIVATE METHODS */
    private void savePositions(@NotNull List<WrappedFigure> figures) {
        for(WrappedFigure selectedFigure : figures) {
            int i = 0;
            for(WrappedFigure figure : state.figures()) {
                if(figure == selectedFigure) {
                    before.add(new PositionData(selectedFigure, i));
                }
                i++;
            }
        }
    }

    private void applyChanges(@NotNull SortedSet<PositionData> positionData) {
        for(PositionData position : positionData) {
            state.figures().remove(position.getFigure());
        }
        for(PositionData position : positionData) {
            state.figures().add(position.getPosition(), position.getFigure());
        }
    }
}