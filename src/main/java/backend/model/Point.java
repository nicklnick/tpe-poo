package backend.model;

public class Point implements Movable{

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public double distanceTo(Point other){
        return Math.sqrt(Math.pow(Math.abs(x - other.getX()),2) + Math.pow(Math.abs(y - other.getY()),2));
    }
}
