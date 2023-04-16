package project.library.dto.response;

import java.util.List;

public class BookRegistrationResponse {

    private final String title;
    private final String author;
    private final String coverImage;
    private final List<String> genres;
    private final String editor;
    private final Short releaseYear;

    public BookRegistrationResponse(String title, String author, String coverImage, List<String> genres, String editor, Short releaseYear) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getEditor() {
        return editor;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }
}
