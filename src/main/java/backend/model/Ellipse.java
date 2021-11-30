package backend.model;

public class Ellipse extends Rectangle {
    private final double sMayorAxis, sMinorAxis;

    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);

        double aux1 = bottomRight.getX() - topLeft.getX();
        double aux2 = bottomRight.getY() - topLeft.getY();
        if(aux1 > aux2) {
            sMayorAxis = aux1;
            sMinorAxis = aux2;
        }
        else {
            sMayorAxis = aux2;
            sMinorAxis = aux1;
        }
    }

    @Override
    public String toString() {
        return String.format("Elipse [DMayor: %.2f, DMenor: %.2f]", sMayorAxis, sMinorAxis);
    }
}
