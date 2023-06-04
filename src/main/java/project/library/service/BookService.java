package project.library.service;

import org.springframework.stereotype.Service;
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
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ClientService clientService;
    private final GenreService genreService;
    private final BookResponseFactory bookResponseFactory;
    private final BookFactory bookFactory;

    public BookService(BookRepository bookRepository, ClientService clientService, GenreService genreService, BookResponseFactory bookResponseFactory, BookFactory bookFactory) {
        this.bookRepository = bookRepository;
        this.clientService = clientService;
        this.genreService = genreService;
        this.bookResponseFactory = bookResponseFactory;
        this.bookFactory = bookFactory;
    }

    public BookResponse saveBook(BookRegistrationRequest bookRegistrationRequest) {

        checkBookDoesNotExist(bookRegistrationRequest.getTitle(), bookRegistrationRequest.getAuthor());

        List<Genre> genres = new ArrayList<>();

        bookRegistrationRequest.getGenres().forEach(genre -> {
            genres.add(genreService.findGenre(genre));
        });

        final Client client = clientService.findClient(bookRegistrationRequest.getClientId());

        final Book savedBook = bookRepository.save(bookFactory.getBook(bookRegistrationRequest, genres, client));

        System.out.println("saved Book: " + savedBook.toString());

        return bookResponseFactory.getBookResponse(savedBook);
    }

    private void checkBookDoesNotExist(String title, String author) {
        Optional<Book> book = bookRepository.findByTitleAndAuthor(title, author);

        if (book.isPresent()) {
            throw new BookAlreadyExistsException("O livro " + title + " do(a) autor(a) " + author + " já se existe em nossos registros! Por favor cadastre um livro ainda não cadastrado!");
        }
    }

    public List<BookResponse> findClientBookList(Long clientId) {
        return bookResponseFactory.getBookResponseList(bookRepository.findByClientListClientId(clientId));
    }

    public List<BookResponse> findBooksByGenre(String genreName) {
        return bookResponseFactory.getBookResponseList(bookRepository.findByGenresGenreName(genreName));
    }

    public void linkBookToClient(BookLinkingRequest bookLinkingRequest) {
        final Client client = clientService.findClient(bookLinkingRequest.getClientId());
        final Book book = findBook(bookLinkingRequest.getBookId());

        final List<Client> clients = book.getClientList();
        clients.add(client);
        book.setClientList(clients);

        bookRepository.save(book);
    }

    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotExistException("Livro não cadastrado!"));
    }
}
