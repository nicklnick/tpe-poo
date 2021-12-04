package backend;

import java.util.ArrayList;
import java.util.List;

/*
**  Encargado de mantener el estado del Canvas
 */
public class CanvasState<V> {

    private final List<V> list = new ArrayList<>();

    public void addFigure(V figure) {
        list.add(figure);
    }

    public List<V> figures() {
        return list;
    }
}
