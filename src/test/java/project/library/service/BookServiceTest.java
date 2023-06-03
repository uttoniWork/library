package project.library.service;

import org.junit.jupiter.api.Test;
import project.library.dto.request.BookLinkingRequest;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookResponse;
import project.library.exception.BookAlreadyExistsException;
import project.library.exception.BookNotExistException;
import project.library.factory.BookFactory;
import project.library.factory.BookResponseFactory;
import project.library.model.Book;
import project.library.model.Client;
import project.library.model.Genre;
import project.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class BookServiceTest {

    private static final Long CLIENT_ID = 1l;
    private static final Long BOOK_ID = 1l;
    private static final String TITLE = "Eragon";
    private static final String AUTHOR = "Christopher Paolini";
    private static final String IMAGE_LINK = "link";
    private static final String EDITOR = "Rocco";
    private static final Short RELEASE_YEAR = 2006;
    private static final String CLIENT_NAME = "Harry";
    private static final String CLIENT_EMAIL = "harry@gmail.com";
    private static final String CLIENT_PASSWORD = "1234";
    private static final List<String> GENRE_NAMES = Arrays.asList("Ficção Científica", "Fantasia");
    private static final List<Genre> GENRE_LIST = Arrays.asList(new Genre(1l, GENRE_NAMES.get(0)), new Genre(2l, GENRE_NAMES.get(1)));
    private static final String BOOK_NOT_EXIST_EXCEPTION_MESSAGE = "Livro não cadastrado!";

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final ClientService clientService = mock(ClientService.class);
    private final GenreService genreService = mock(GenreService.class);
    private final BookResponseFactory bookResponseFactory = new BookResponseFactory();
    private final BookFactory bookFactory = new BookFactory();
    private final BookService bookService = new BookService(bookRepository, clientService, genreService, bookResponseFactory, bookFactory);

    @Test
    public void shouldSaveBook() {

        when(bookRepository.findByTitleAndAuthor(anyString(), anyString())).thenReturn(Optional.empty());
        when(genreService.findGenre(GENRE_NAMES.get(0))).thenReturn(GENRE_LIST.get(0));
        when(genreService.findGenre(GENRE_NAMES.get(1))).thenReturn(GENRE_LIST.get(1));
        when(clientService.findClient(anyLong())).thenReturn(getClient());
        when(bookRepository.save(any(Book.class))).thenReturn(getBook(Arrays.asList(getClient())));

        final BookResponse actualBookResponse = bookService.saveBook(getBookRegistrationRequest());

        final BookResponse expectedBookResponse = getBookResponse();

        assertThat(actualBookResponse).usingRecursiveComparison().isEqualTo(expectedBookResponse);
        verify(bookRepository, times(1)).findByTitleAndAuthor(anyString(), anyString());
        verify(genreService, times(2)).findGenre(anyString());
        verify(clientService, times(1)).findClient(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void shouldNotSaveBookAndThrowBookAlreadyExistsException() {

        when(bookRepository.findByTitleAndAuthor(anyString(), anyString())).thenReturn(Optional.of(getBook(Arrays.asList(getClient()))));

        final BookAlreadyExistsException actualBookAlreadyExistsException = assertThrows(BookAlreadyExistsException.class, () -> {
            bookService.saveBook(getBookRegistrationRequest());
        });

        final BookAlreadyExistsException expectedBookAlreadyExistsException = getBookAlreadyExistsException(TITLE, AUTHOR);

        assertThat(actualBookAlreadyExistsException).usingRecursiveComparison().isEqualTo(expectedBookAlreadyExistsException);
        verify(bookRepository, times(1)).findByTitleAndAuthor(anyString(), anyString());
    }

    @Test
    public void shouldFindClientBookList() {
        when(bookRepository.findByClientListClientId(anyLong())).thenReturn(Arrays.asList(getBook(Arrays.asList(getClient()))));

        final List<BookResponse> actualBookResponseList = bookService.findClientBookList(CLIENT_ID);
        final List<BookResponse> expectedBookResponseList = Arrays.asList(getBookResponse());

        assertThat(actualBookResponseList).usingRecursiveComparison().isEqualTo(expectedBookResponseList);
        verify(bookRepository, times(1)).findByClientListClientId(anyLong());
    }

    @Test
    public void findBooksByGenre() {
        when(bookRepository.findByGenresGenreName(anyString())).thenReturn(Arrays.asList(getBook(Arrays.asList(getClient()))));

        final List<BookResponse> actualBookResponseList = bookService.findBooksByGenre(GENRE_NAMES.get(0));
        final List<BookResponse> expectedBookResponseList = Arrays.asList(getBookResponse());

        assertThat(actualBookResponseList).usingRecursiveComparison().isEqualTo(expectedBookResponseList);
        verify(bookRepository, times(1)).findByGenresGenreName(anyString());
    }

    @Test
    public void shouldLinkBookToClient() {

        when(clientService.findClient(anyLong())).thenReturn(getClient());
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(getBook(getClientList(getClient()))));
        when(bookRepository.save(any(Book.class))).thenReturn(getBook(getClientList(getClient())));

        bookService.linkBookToClient(getBookLinkingRequest());

        verify(clientService, times(1)).findClient(anyLong());
        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void shouldFindBook() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(getBook(getClientList(getClient()))));

        final Book actualBook = bookService.findBook(BOOK_ID);
        final Book expectedBook = getBook(getClientList(getClient()));

        assertEquals(expectedBook, actualBook);
        verify(bookRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldNotFindBookAndThrowBookNotExistException() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        final BookNotExistException actualBookNotExistException = assertThrows(BookNotExistException.class, () -> {
            bookService.findBook(BOOK_ID);
        });

        final BookNotExistException expectedBookNotExistException = getBookNotExistException();

        assertThat(actualBookNotExistException).usingRecursiveComparison().isEqualTo(expectedBookNotExistException);
        verify(bookRepository, times(1)).findById(anyLong());
    }

    private BookRegistrationRequest getBookRegistrationRequest() {
        return new BookRegistrationRequest(CLIENT_ID, TITLE, AUTHOR, IMAGE_LINK, GENRE_NAMES, EDITOR, RELEASE_YEAR);
    }

    private BookResponse getBookResponse() {
        return new BookResponse(BOOK_ID, TITLE, AUTHOR, IMAGE_LINK, GENRE_LIST, EDITOR, RELEASE_YEAR);
    }

    private BookLinkingRequest getBookLinkingRequest() {
        return new BookLinkingRequest(CLIENT_ID, BOOK_ID);
    }

    private Book getBook(List<Client> clients) {
        final Book book = new Book(TITLE, AUTHOR, IMAGE_LINK, GENRE_LIST, EDITOR, RELEASE_YEAR, clients);
        book.setBookId(BOOK_ID);

        return book;
    }

    private BookNotExistException getBookNotExistException() {
        return new BookNotExistException(BOOK_NOT_EXIST_EXCEPTION_MESSAGE);
    }

    private BookAlreadyExistsException getBookAlreadyExistsException(String title, String author) {
        return new BookAlreadyExistsException("O livro " + title + " do(a) autor(a) " + author + " já se existe em nossos registros! Por favor cadastre um livro ainda não cadastrado!");
    }

    private Client getClient() {
        final Client client = new Client(CLIENT_NAME, CLIENT_EMAIL, CLIENT_PASSWORD);
        client.setClientId(CLIENT_ID);

        return client;
    }

    private List<Client> getClientList(Client client) {
        final List<Client> clients = new ArrayList<>();
        clients.add(client);

        return clients;
    }
}