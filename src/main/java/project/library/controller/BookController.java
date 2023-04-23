package project.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookRegistrationResponse;
import project.library.dto.response.BookResponse;
import project.library.model.Book;
import project.library.service.BookService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BookResponse>> getClientBookList(@RequestParam Long clientId){

        return ResponseEntity.ok(bookService.findClientBookList(clientId));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genreName){

        return ResponseEntity.ok(bookService.findBooksByGenre(genreName));
    }
}
