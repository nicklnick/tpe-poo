package frontend.data;

import javafx.scene.paint.Color;

public class EdgeData {
    private int id;
    private int width;

    public EdgeData(int id, int width){
        this.id = id;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }
}
