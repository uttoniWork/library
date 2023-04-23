package project.library.factory;

import project.library.dto.response.BookResponse;
import project.library.model.Book;

import java.util.ArrayList;
import java.util.List;

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
