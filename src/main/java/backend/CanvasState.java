package backend;

import backend.model.Figure;
import frontend.wrappers.WrappedFigure;

import java.util.ArrayList;
import java.util.List;

public class CanvasState<V> {

    private final List<V> list = new ArrayList<>();

    public void addFigure(V figure) {
        list.add(figure);
    }

    public List<V> figures() {
        return list;
    }

    public void addFigures(List<V> figures) {
        list.addAll(figures);
    }

    public void clearFigures() {
        list.clear();
    }


}
