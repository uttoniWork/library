package project.library.repository;

import org.springframework.data.repository.CrudRepository;
import project.library.model.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
}
