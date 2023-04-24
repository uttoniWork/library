package project.library.service;

import org.springframework.stereotype.Service;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookResponse;
import project.library.exception.BookAlreadyExistsException;
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
            genres.add(genreService.findGenre(genre.getGenreId()));
        });

        final Client client = clientService.findClient(bookRegistrationRequest.getClientId());

        final Book savedBook = bookRepository.save(bookFactory.getBook(bookRegistrationRequest, genres, client));

        return bookResponseFactory.getBookResponse(savedBook);
    }

    private void checkBookDoesNotExist(String title, String author){
        Optional<Book> book = bookRepository.findByTitleAndAuthor(title, author);

        if(book.isPresent()){
            throw new BookAlreadyExistsException("O livro " + title + " do(a) autor(a) " + author + " já se existe em nossos registros! Por favor cadastre um livro ainda não cadastrado!");
        }
    }

    public List<BookResponse> findClientBookList(Long clientId) {
        return bookResponseFactory.getBookResponseList(bookRepository.findByClientClientId(clientId));
    }

    public List<Book> findBooksByGenre(String genreName) {
        return bookRepository.findByGenresGenreName(genreName);
    }
}
