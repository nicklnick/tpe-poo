package frontend.actions;

import backend.CanvasState;
import frontend.data.ColorData;
import frontend.wrappers.WrappedFigure;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColorFillAction extends GraphicAction<ColorData> {

    private final Color after;

    public ColorFillAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures, Color after) {
        super(state, figures);
        this.after = after;
    }

    @Override
    protected void savePrevious(@NotNull List<WrappedFigure> figures) {
        for(WrappedFigure figure : figures) {
            before.add(new ColorData(figure.getId(), figure.getFillColor()));
        }
    }

    @Override
    public void undo() {
        for(WrappedFigure figure : state.figures()) {
            for(ColorData data : before) {
                if(figure.getId() == data.getId()) {
                    figure.setFillColor(data.getColor());
                }
            }
        }
    }

    @Override
    public void redo() {
        for(WrappedFigure figure : state.figures()) {
            for(ColorData data : before){
                if(figure.getId() == data.getId()) {
                    figure.setFillColor(after);
                }
            }
        }
    }
}
