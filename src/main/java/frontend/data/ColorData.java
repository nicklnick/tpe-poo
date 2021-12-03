package frontend.data;

import javafx.scene.paint.Color;

public class ColorData {
    private int id;
    private Color color;

    public ColorData(int id, Color color){
        this.id = id;
        this.color = color;
    }

    public int getId(){
        return id;
    }
    public Color getColor(){
        return color;
    }
}
