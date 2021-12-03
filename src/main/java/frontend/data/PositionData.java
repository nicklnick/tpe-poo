package frontend.data;

import frontend.wrappers.WrappedFigure;

public class PositionData implements Comparable<PositionData>{
    private WrappedFigure figure;
    private int position;

    public PositionData(WrappedFigure figure, int position){
        this.figure = figure;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public WrappedFigure getFigure() {
        return figure;
    }

    @Override
    public int compareTo(PositionData o) {
        return Integer.compare(position, o.getPosition());
    }
}
