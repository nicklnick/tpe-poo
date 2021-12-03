package frontend.data;

import javafx.scene.paint.Color;

public class EdgeData {
    private int id;
    private double width;

    public EdgeData(int id, double width){
        this.id = id;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public double getWidth() {
        return width;
    }
}
