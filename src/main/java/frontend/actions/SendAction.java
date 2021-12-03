package frontend.actions;

import backend.CanvasState;
import frontend.data.PositionData;
import frontend.wrappers.WrappedFigure;

import java.util.*;

public abstract class SendAction extends CustomAction {
    protected SortedSet<PositionData> before = new TreeSet<>();
    protected SortedSet<PositionData> after = new TreeSet<>();

    public SendAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state);
        savePositions(figures);
    }

    private void savePositions(List<WrappedFigure> figures) {
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

    @Override
    public void undo() {
        applyChanges(before);
    }

    @Override
    public void redo() {
        applyChanges(after);
    }

    private void applyChanges(SortedSet<PositionData> positionData) {
        for(PositionData position : positionData) {
            state.figures().remove(position.getFigure());
        }
        for(PositionData position : positionData) {
            state.figures().add(position.getPosition(), position.getFigure());
        }
    }
}
