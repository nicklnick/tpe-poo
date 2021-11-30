package backend.model;

public abstract class Figure implements Movable{
    public abstract void move(double x, double y);
    public abstract boolean figureBelongs(Point point);
    public abstract boolean encapsulatedIn(Point startPoint, Point endPoint);
    public abstract double getX();
    public abstract double getY();
    public abstract double getHeight();
    public abstract double getWidth();
}
