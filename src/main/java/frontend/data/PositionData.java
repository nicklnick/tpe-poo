package frontend.data;

import frontend.wrappers.WrappedFigure;
import org.jetbrains.annotations.NotNull;

/*
**  Guarda los datos de la posicion que ocupa una figura en un array
 */
public class PositionData implements Comparable<PositionData> {

    private final WrappedFigure figure;
    private final int position;               // Posicion de la figura en el array

    public PositionData(WrappedFigure figure, int position) {
        this.figure = figure;
        this.position = position;
    }

    @Override
    public int compareTo(@NotNull PositionData o) {
        return Integer.compare(position, o.getPosition());
    }

    /* GETTERS */
    public int getPosition() {
        return position;
    }
    public WrappedFigure getFigure() {
        return figure;
    }
}
