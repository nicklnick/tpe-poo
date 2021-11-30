package backend.model;

public class Circle extends Figure {

    private final Point centerPoint;
    private final double radius;

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
        return centerPoint.distanceTo(point) < radius;
    }

    @Override
    public double getX() {
        return centerPoint.getX() - radius;
    }

    @Override
    public double getY() {
        return centerPoint.getY() - radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getWidth() {
        return getHeight();        //!!!!!!!!!!!!!!!!!!
    }

    @Override
    public void move(double x, double y) {
        centerPoint.move(x,y);
    }


}
