package project.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByGenreName(String genreName);
}
