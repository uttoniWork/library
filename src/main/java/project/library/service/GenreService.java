package project.library.service;

import org.springframework.stereotype.Service;
import project.library.model.Genre;
import project.library.repository.GenreRepository;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findGenre(String genreName) {
        return genreRepository.findByGenreName(genreName).get();
    }
}
