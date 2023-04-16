package project.library.service;

import org.springframework.stereotype.Service;
import project.library.dto.request.BookRegistrationRequest;
import project.library.dto.response.BookRegistrationResponse;
import project.library.exception.BookAlreadyExistsException;
import project.library.model.Book;
import project.library.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRegistrationResponse saveBook(BookRegistrationRequest bookRegistrationRequest) {

        checkBookDoesNotExist(bookRegistrationRequest.getTitle(), bookRegistrationRequest.getAuthor());

        final Book book = new Book(
                bookRegistrationRequest.getTitle(),
                bookRegistrationRequest.getAuthor(),
                bookRegistrationRequest.getCoverImage(),
                bookRegistrationRequest.getGenres(),
                bookRegistrationRequest.getEditor(),
                bookRegistrationRequest.getReleaseYear()
        );

        final Book savedBook = bookRepository.save(book);

        return new BookRegistrationResponse(
                savedBook.getTitle(),
                savedBook.getAuthor(),
                savedBook.getCoverImage(),
                savedBook.getGenres(),
                savedBook.getEditor(),
                savedBook.getReleaseYear()
        );
    }

    private void checkBookDoesNotExist(String title, String author){
        Optional<Book> book = bookRepository.findByTitleAndAuthor(title, author);

        if(book.isPresent()){
            throw new BookAlreadyExistsException("O livro " + title + " do(a) autor(a) " + author + " já se existe em nossos registros! Por favor cadastre um livro ainda não cadastrado!");
        }
    }
}
