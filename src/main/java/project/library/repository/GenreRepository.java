package project.library.repository;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByGenreName(String genreName);
    @Query("MATCH (b:book)-[:EH_DO_GENERO]->(g:genre) where ID(b)=$bookId return g")
    List<Genre> findGenresByBookId(Long bookId);
}
