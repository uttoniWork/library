package project.library.factory;

import org.springframework.stereotype.Component;
import project.library.dto.response.BookResponse;
import project.library.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookResponseFactory {

    public BookResponse getBookResponse(Book book) {
        return new BookResponse(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCoverImage(),
                book.getGenres(),
                book.getEditor(),
                book.getReleaseYear()
        );
    }

    public List<BookResponse> getBookResponseList(List<Book> books){

        final List<BookResponse> bookResponseList = new ArrayList<>();

        books.forEach(book -> {
            bookResponseList.add(getBookResponse(book));
        });

        return bookResponseList;
    }
}
