package project.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;
import project.library.model.Genre;
import project.library.repository.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GenreServiceTest {

    private static final Long GENRE_ID = 1L;
    private static final String GENRE_NAME = "Ficção Científica";

    private final GenreRepository genreRepository = mock(GenreRepository.class);
    private final GenreService genreService = new GenreService(genreRepository);

    @Test
    public void shouldFindGenre() {
        when(genreRepository.findByGenreName(anyString())).thenReturn(Optional.of(getGenre()));

        final Genre actualGenre = genreService.findGenre(GENRE_NAME);
        final Genre expectedGenre = getGenre();

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
        verify(genreRepository, times(1)).findByGenreName(anyString());
    }

    private Genre getGenre() {
        return new Genre(GENRE_ID, GENRE_NAME);
    }
}