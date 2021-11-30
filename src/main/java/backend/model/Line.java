package backend.model;

public class Line extends Figure{
    private final Point firstPoint, secondPoint;
    public Line(Point firstPoint, Point secondPoint){
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    @Override
    public String toString(){
        return String.format("Linea [ %s , %s ]",firstPoint, secondPoint);
    }

    @Override
    public void move(double x, double y) {
        firstPoint.move(x,y);
        secondPoint.move(x,y);
    }

    private double gradient(){
        return (firstPoint.getY() - secondPoint.getY())/(firstPoint.getX()- secondPoint.getX());
    }

    private double calcB(){
        return firstPoint.getY() - gradient()* firstPoint.getX();
    }

    private boolean betweenPointsX(Point point){
        return (point.getX() >= firstPoint.getX() && point.getX() <= secondPoint.getX())
                || (point.getX() <= firstPoint.getX() && point.getX() >= secondPoint.getX());
    }

    @Override
    public boolean figureBelongs(Point point) {
        return betweenPointsX(point) && Double.compare(point.getY(), gradient()* point.getX() + calcB()) == 0;
    }

}
