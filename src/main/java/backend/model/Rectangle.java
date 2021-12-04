package backend.model;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void move(double diffX, double diffY) {
        topLeft.move(diffX, diffY);
        bottomRight.move(diffX, diffY);
    }

    @Override
    public boolean contains(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX()
                && point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }


    /* GETTERS */
    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public Point getFirstPoint() {
        return getTopLeft();
    }

    @Override
    public Point getSecondPoint() {
        return getBottomRight();
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
