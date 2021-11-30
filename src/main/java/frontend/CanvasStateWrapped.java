package frontend;

import backend.model.Figure;
import frontend.wrappers.WrappedFigure;

import java.util.ArrayList;
import java.util.List;

public class CanvasStateWrapped {

    private final List<WrappedFigure> list = new ArrayList<>();

    public void addFigure(WrappedFigure figure) {
        list.add(figure);
    }

    public void addFigures(List<WrappedFigure> figures) {
        list.addAll(figures);
    }

    public void clearFigures() {
        list.clear();
    }
    public Iterable<WrappedFigure> figures() {
        return new ArrayList<>(list);
    }

}
