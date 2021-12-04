package frontend.actions;

import backend.CanvasState;
import frontend.data.PositionData;
import frontend.wrappers.WrappedFigure;

import java.util.List;

public class SendToBackAction extends SendAction {
    public SendToBackAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures) {
        super(state, figures);
    }

    @Override
    protected void saveAfter() {
        int newPosition = 0;
        for(PositionData position : before) {
            after.add(new PositionData(position.getFigure(), newPosition++));
        }
    }
}
