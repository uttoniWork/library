package project.library.exception;

public class BookNotExistException extends RuntimeException{

    public BookNotExistException(String message){
        super(message);
    }
}
