package frontend.Exceptions;

public class NothingToUndoException extends RuntimeException{
    private static final String MESSAGE = "Nada para deshacer!";

    public NothingToUndoException(){
        super(MESSAGE);
    }
}
