package frontend.actions;

import backend.CanvasState;
import frontend.data.EdgeData;
import frontend.wrappers.WrappedFigure;

import java.util.LinkedList;
import java.util.List;

public class WidthAction extends CustomAction{

    private List<EdgeData> before = new LinkedList<>();
    private double after;

    public WidthAction(CanvasState<WrappedFigure> state, List<WrappedFigure> figures, double after){
        super(state);
        this.after = after;
        savePrevious(figures);
    }

    @Override
    public void undo() {
        for( WrappedFigure figure : state.figures()){
            for( EdgeData data : before){
                if( figure.getId() == data.getId()){
                    figure.setEdgeWidth(data.getWidth());
                }
            }
        }
    }

    @Override
    public void redo() {
        for( WrappedFigure figure : state.figures()){
            for( EdgeData data : before){
                if( figure.getId() == data.getId()){
                    figure.setEdgeWidth(after);
                }
            }
        }
    }

    @Override
    protected void savePrevious(List<WrappedFigure> figures) {
        for(WrappedFigure figure : figures){
            before.add(new EdgeData(figure.getId(), figure.getEdgeWidth()));
        }
    }
}
