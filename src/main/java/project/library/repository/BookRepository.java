package project.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
    List<Book> findByClientListClientId(Long clientId);
    List<Book> findByGenresGenreName(String genreName);
    List<Book> findByTitle(String bookTitle);
}
