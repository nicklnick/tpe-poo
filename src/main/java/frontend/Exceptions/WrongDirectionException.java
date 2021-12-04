package frontend.Exceptions;

public class WrongDirectionException extends RuntimeException{

    private static final String MESSAGE = "La figura deberia ser trazada de el punto superior izquierdo al inferior derecho";

    public WrongDirectionException(){
        super(MESSAGE);
    }
}
