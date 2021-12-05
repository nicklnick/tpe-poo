package frontend.data;

/*
 **  Guarda los datos relacionados al color de una figura
 */
public class EdgeData {

    private final int id;             // ID de la figura
    private final double width;       // Grosor de la figura

    public EdgeData(int id, double width){
        this.id = id;
        this.width = width;
    }

    /* GETTERS */
    public int getId() {
        return id;
    }
    public double getWidth() {
        return width;
    }
}
