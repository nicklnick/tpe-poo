package backend.model;

public abstract class Figure implements Movable{
    /* Metodos para ImaginaryBox */
    public abstract void move(double diffX, double diffY);
    public abstract boolean contains(Point point);
    public abstract Point getFirstPoint();
    public abstract Point getSecondPoint();

    public abstract double getX();
    public abstract double getY();
    public abstract double getHeight();
    public abstract double getWidth();
}
