package project.library.dto.response;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import project.library.model.Client;
import project.library.model.Genre;

import java.time.LocalDateTime;
import java.util.List;

public class BookResponse {

    private Long bookId;
    private String title;
    private String author;
    private String coverImage;
    @Relationship(type = "EH_DO_GENERO", direction = Relationship.Direction.OUTGOING)
    private List<Genre> genres;
    private String editor;
    private Short releaseYear;

    public BookResponse() {
    }

    public BookResponse(Long bookId, String title, String author, String coverImage, List<Genre> genres, String editor, Short releaseYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }
}
