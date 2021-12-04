package backend.model;

public class Point implements Movable{

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public void move(double diffX, double diffY) {
        this.x += diffX;
        this.y += diffY;
    }

    public double distanceTo(Point other){
        return Math.sqrt(Math.pow(Math.abs(x - other.getX()),2) + Math.pow(Math.abs(y - other.getY()),2));
    }


    /* GETTERS */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
