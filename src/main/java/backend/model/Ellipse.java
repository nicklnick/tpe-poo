package backend.model;

public class Ellipse extends Rectangle {
    private final double xAxis, yAxis;

    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);

        xAxis = bottomRight.getX() - topLeft.getX();
        yAxis = bottomRight.getY() - topLeft.getY();
    }

    public double getxAxis() { return xAxis; }
    public double getyAxis() { return yAxis; }

    @Override
    public String toString() {
        return String.format("Elipse [EjeX: %.2f, EjeY: %.2f]", xAxis, yAxis);
    }
}
