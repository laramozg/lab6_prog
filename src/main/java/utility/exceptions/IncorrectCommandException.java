package utility.exceptions;

public class IncorrectCommandException extends RuntimeException{
    public IncorrectCommandException(String massage){
        super(massage);
    }
}
