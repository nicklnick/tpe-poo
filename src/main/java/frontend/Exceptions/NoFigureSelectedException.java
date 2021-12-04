package frontend.Exceptions;

public class NoFigureSelectedException extends RuntimeException{
    private static final String MESSAGE = "Ninguna figura seleccionada";

    public NoFigureSelectedException(){
        super(MESSAGE);
    }
}
