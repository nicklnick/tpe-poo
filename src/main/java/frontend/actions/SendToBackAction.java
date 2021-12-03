package frontend.actions;

import backend.CanvasState;
import frontend.wrappers.WrappedFigure;

import java.util.List;

public class SendToBackAction extends SendAction {
    public SendToBackAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state, figures);
        saveAfter();
    }

    private void saveAfter() {
        int newPosition = 0;
        for(PositionData position : before) {
            after.add(new PositionData(position.getFigure(), newPosition++));
        }
    }
}
