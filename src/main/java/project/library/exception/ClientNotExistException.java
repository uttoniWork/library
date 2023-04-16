package project.library.exception;

public class ClientNotExistException extends RuntimeException{
    public ClientNotExistException(String message) {
        super(message);
    }
}
