package backend.model;

public class Circle extends Figure {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean figureBelongs(Point point) {
        return Math.sqrt(Math.pow(centerPoint.getX() - point.getX(), 2) +
                Math.pow(centerPoint.getY() - point.getY(), 2)) < radius;
    }

    @Override
    public void move(double x, double y) {
        centerPoint.move(x,y);
    }
}
