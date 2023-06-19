package project.library.repository;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Book;
import project.library.model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
    List<Book> findByClientListClientId(Long clientId);
    List<Book> findByGenresGenreName(String genreName);
    List<Book> findByTitle(String bookTitle);

    @Query("MATCH (b:book)-[:EH_DO_GENERO]->(g:genre) WHERE g.genreName IN $genreNames RETURN b, COLLECT(g) as genres")
    List<Book> findBooksByGenres(List<String> genreNames);
    @Query("MATCH (b:book)-[:EH_DO_GENERO]->(g:genre) WHERE ID(b) IN $bookIds RETURN g")
    List<Genre> findGenresByBookIds(List<Long> bookIds);
}
