package frontend.Exceptions;

public class HistoryStackException extends RuntimeException{

    public HistoryStackException(String message){
        super(String.format("Nada para %s!", message));
    }
}
