package project.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.http.ResponseEntity;
import project.library.dto.request.BookLinkingRequest;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookResponse;
import project.library.model.Book;
import project.library.model.Genre;
import project.library.service.BookService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    private static final Long CLIENT_ID = 1l;
    private static final Long BOOK_ID = 1l;
    private static final String TITLE = "Eragon";
    private static final String AUTHOR = "Christopher Paolini";
    private static final String IMAGE_LINK = "link";
    private static final List<String> GENRE_NAMES = Arrays.asList("Ficção Científica", "Fantasia");
    private static final List<Genre> GENRE_LIST = Arrays.asList(new Genre(1l, "Ficção Científica"), new Genre(2l, "Fantasia"));
    private static final String EDITOR = "Rocco";
    private static final Short RELEASE_YEAR = 2006;
    private static final String SEARCHED_GENRE = "Ficção Científica";

    private final BookService bookService = mock(BookService.class);
    private final BookController bookController = new BookController(bookService);

    @Test
    public void shouldPostBook() {

        when(bookService.saveBook(any(BookRegistrationRequest.class))).thenReturn(getBookResponse());

        final ResponseEntity<BookResponse> actualResponse = bookController.postBook(getBookRegistrationRequest());

        final ResponseEntity<BookResponse> expectedResponse = ResponseEntity.ok(getBookResponse());

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void shouldGetClientBookList() {
        when(bookService.findClientBookList(anyLong())).thenReturn(Arrays.asList(getBookResponse()));

        final ResponseEntity<List<BookResponse>> actualResponse = bookController.getClientBookList(CLIENT_ID);

        final ResponseEntity<List<BookResponse>> expectedResponse = ResponseEntity.ok(Arrays.asList(getBookResponse()));

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void shouldGetBooksByGenre() {
        when(bookService.findBooksByGenre(anyString())).thenReturn(Arrays.asList(getBookResponse()));

        final ResponseEntity<List<BookResponse>> actualResponse = bookController.getBooksByGenre(SEARCHED_GENRE);

        final ResponseEntity<List<BookResponse>> expectedResponse = ResponseEntity.ok(Arrays.asList(getBookResponse()));

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void shouldLinkBookToClient() {
        doNothing().when(bookService).linkBookToClient(any(BookLinkingRequest.class));

        bookController.linkBookToClient(getBookLinkingRequest());

        verify(bookService, times(1)).linkBookToClient(any(BookLinkingRequest.class));
    }

    private BookRegistrationRequest getBookRegistrationRequest() {
        return new BookRegistrationRequest(CLIENT_ID, TITLE, AUTHOR, IMAGE_LINK, GENRE_NAMES, EDITOR, RELEASE_YEAR);
    }

    private BookResponse getBookResponse() {
        return new BookResponse(BOOK_ID, TITLE, AUTHOR, IMAGE_LINK, GENRE_LIST, EDITOR, RELEASE_YEAR);
    }

    private BookLinkingRequest getBookLinkingRequest(){
        return new BookLinkingRequest(CLIENT_ID, BOOK_ID);
    }
}