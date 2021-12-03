package frontend.actions;

import backend.CanvasState;
import frontend.data.PositionData;
import frontend.wrappers.WrappedFigure;

import java.util.*;

public class DeleteAction extends CustomAction{
    private SortedSet<PositionData> before = new TreeSet<>();

    public DeleteAction(CanvasState<WrappedFigure> state, List<WrappedFigure> selectedFigures){
        super(state);
        savePrevious(selectedFigures);
    }
    protected void savePrevious(List<WrappedFigure> figures){
        ListIterator<WrappedFigure> iterator = state.figures().listIterator();
        while(iterator.hasNext()){
            WrappedFigure current= iterator.next();

            for(WrappedFigure selected : figures){
                if(selected.getId() == current.getId()){
                    before.add(new PositionData(selected, iterator.previousIndex()));
                }
            }
        }
    }

    @Override
    public void undo() {
        for(PositionData deletedFig : before){
            state.figures().add(deletedFig.getPosition(), deletedFig.getFigure());
        }
    }

    @Override
    public void redo() {
        for(PositionData figure : before){
            state.figures().remove(figure.getFigure());
        }

    }
}
