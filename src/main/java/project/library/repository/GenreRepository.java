package project.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Genre;

import java.util.List;
import java.util.Map;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByGenreId(Long genreId);
}
