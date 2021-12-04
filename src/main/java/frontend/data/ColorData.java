package frontend.data;

import javafx.scene.paint.Color;

/*
**  Guarda los datos relacionados al color de una figura
 */
public class ColorData {

    private int id;         // ID de la figura
    private Color color;    // Color de la figura

    public ColorData(int id, Color color){
        this.id = id;
        this.color = color;
    }

    /* GETTERS */
    public int getId(){
        return id;
    }
    public Color getColor(){
        return color;
    }
}
