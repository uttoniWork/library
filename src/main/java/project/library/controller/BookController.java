package project.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.dto.request.BookLinkingRequest;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookResponse;
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
    public ResponseEntity<BookResponse> postBook(@RequestBody BookRegistrationRequest bookRegistrationRequest) {

        System.out.println("Livro novo: " + bookRegistrationRequest.toString());

        final BookResponse bookResponse = bookService.saveBook(bookRegistrationRequest);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getClientBookList(@RequestParam Long clientId) {

        return ResponseEntity.ok(bookService.findClientBookList(clientId));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<BookResponse>> getBooksByGenre(@RequestParam String genreName) {

        return ResponseEntity.ok(bookService.findBooksByGenre(genreName));
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookResponse>> getBooksByName(@RequestParam String bookTitle) {

        return ResponseEntity.ok(bookService.findBooksByName(bookTitle));
    }

    @PostMapping("/choose")
    public ResponseEntity<String> linkBookToClient(@RequestBody BookLinkingRequest bookLinkingRequest) {

        bookService.linkBookToClient(bookLinkingRequest);

        return ResponseEntity.ok("Livro adicionado com sucesso!");
    }
}
