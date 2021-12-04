package backend.model;

/*
** Representa la cantidad de posiciones que se movera la figura/punto
 */
@FunctionalInterface
public interface Movable {
    void move(double diffX, double diffY);
}
