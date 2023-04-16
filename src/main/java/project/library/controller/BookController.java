package project.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookRegistrationResponse;
import project.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookRegistrationResponse> postBook(@RequestBody BookRegistrationRequest bookRegistrationRequest){

        final BookRegistrationResponse bookRegistrationResponse = bookService.saveBook(bookRegistrationRequest);

        return ResponseEntity.ok(bookRegistrationResponse);
    }
}
