package backend;

import java.util.ArrayList;
import java.util.List;

/*
**  Encargado de mantener el estado del Canvas.
**  La clase usa un tipo de dato generico para que el front pueda decidir/hacer
**  que las figuras tengan el comportamiento que desea.
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
