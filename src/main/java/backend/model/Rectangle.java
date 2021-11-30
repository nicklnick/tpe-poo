package backend.model;

import java.util.Objects;

public class Rectangle extends Figure {


    protected final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void move(double x, double y) {
        topLeft.move(x,y);
        bottomRight.move(x,y);
    }

    @Override
    public boolean figureBelongs(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX() &&
                point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }

    @Override
    public boolean encapsulatedIn(Point startPoint, Point endPoint) {
        return topLeft.getX() >= startPoint.getX() && topLeft.getY() >= startPoint.getY()
                && bottomRight.getX() <= endPoint.getX() && bottomRight.getY() <= endPoint.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(topLeft, rectangle.topLeft) && Objects.equals(bottomRight, rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }

    @Override
    public double getX() {
        return topLeft.getX();
    }

    @Override
    public double getY() {
        return topLeft.getY();
    }

    @Override
    public double getHeight() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }

    @Override
    public double getWidth() {
        return Math.abs(topLeft.getX() - bottomRight.getX());
    }
}
