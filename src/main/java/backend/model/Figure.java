package backend.model;

public abstract class Figure implements Movable{
    public abstract void move(double x, double y);
    public abstract boolean contains(Point point);
    public abstract Point getFirstPoint();
    public abstract Point getSecondPoint(); //methods for imaginaryBox
    public abstract double getX();
    public abstract double getY();
    public abstract double getHeight();
    public abstract double getWidth();
}
