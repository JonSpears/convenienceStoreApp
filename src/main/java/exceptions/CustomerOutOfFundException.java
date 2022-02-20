package exceptions;

public class CustomerOutOfFundException extends Exception{
    public CustomerOutOfFundException (String message){
        super(message);
    }
}
