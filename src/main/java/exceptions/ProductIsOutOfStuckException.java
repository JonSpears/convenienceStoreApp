package exceptions;

public class ProductIsOutOfStuckException extends Exception{
    public ProductIsOutOfStuckException(String message){
        super(message);
    }
}
