package frontend.actions;

import backend.CanvasState;
import frontend.data.PositionData;
import frontend.wrappers.WrappedFigure;

import java.util.List;

public class SendToFrontAction extends SendAction {
    public SendToFrontAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state, figures);
        saveAfter();
    }

    private void saveAfter() {
        int size = state.figures().size() - 1;
        int newPosition = size - (before.size() - 1);
        for(PositionData position : before) {
            after.add(new PositionData(position.getFigure(), newPosition++));
        }
    }
}
