package backend.model;

public class Square extends Rectangle{
    public Square( Point topLeft, double xCoord){
        super(topLeft, new Point(topLeft.getX()+xCoord, topLeft.getY() + xCoord ));
    }

    @Override
    public String toString(){
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight() );
    }
}
