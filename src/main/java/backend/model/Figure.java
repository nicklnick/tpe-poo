package backend.model;

public abstract class Figure implements Movable{
    public abstract void move(double x, double y);
    public abstract boolean figureBelongs(Point point);
}
