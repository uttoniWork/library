package project.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.library.exception.BookAlreadyExistsException;
import project.library.exception.BookNotExistException;
import project.library.exception.ClientAlreadyExistsException;
import project.library.exception.ClientNotExistException;

@RestControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<String> handleClientNotExistException(ClientNotExistException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BookNotExistException.class)
    public ResponseEntity<String> handleBookNotExistException(BookNotExistException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
