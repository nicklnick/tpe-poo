package backend.model;

public class Line extends Figure{
    private final Point firstPoint, secondPoint;
    private static final double DELTA = 10;
    public Line(Point firstPoint, Point secondPoint){
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }
    @Override
    public Point getFirstPoint() {
        return firstPoint;
    }
    @Override
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
    public boolean contains(Point point) {
        if( Math.abs(firstPoint.getX()- secondPoint.getX()) < DELTA){
            return Math.abs(firstPoint.getX() - point.getX()) < DELTA && ((firstPoint.getY() >= point.getY() && secondPoint.getY() <= point.getY()) ||
                    (firstPoint.getY() <= point.getY() && secondPoint.getY() >= point.getY()));
        }
        return betweenPointsX(point) && Math.abs(point.getY() - (gradient()* point.getX() + calcB())) <  DELTA ;
    }


    @Override
    public double getX(){
        return firstPoint.getX();
    }
    @Override
    public double getY(){
        return firstPoint.getY();
    }
    @Override
    public double getWidth(){
        return firstPoint.getX() - secondPoint.getX();
    }
    @Override
    public double getHeight(){
        return firstPoint.getY() - secondPoint.getY();
    }
}
