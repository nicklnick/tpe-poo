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

    @Override
    public boolean contains(Point point) {
        return centerPoint.distanceTo(point) < radius;
    }

    @Override
    public void move(double diffX, double diffY) {
        centerPoint.move(diffX, diffY);
    }


    /* GETTERS */
    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Point getFirstPoint() {
        return new Point(centerPoint.getX() - radius, centerPoint.getY() - radius);
    }

    @Override
    public Point getSecondPoint() {
        return new Point(centerPoint.getX() + radius, centerPoint.getY() + radius);
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
        return getHeight();
    }
}
