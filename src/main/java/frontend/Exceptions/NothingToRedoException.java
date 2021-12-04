package frontend.Exceptions;

public class NothingToRedoException extends RuntimeException{
    private static final String MESSAGE = "Nada para rehacer!";

    public NothingToRedoException(){
        super(MESSAGE);
    }
}
