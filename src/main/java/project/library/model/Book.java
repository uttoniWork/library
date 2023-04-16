package project.library.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;
import java.util.List;

@Node("book")
public class Book {

    @Id
    @GeneratedValue
    private Long bookId;
    @Property
    private final String title;
    @Property
    private final String author;
    @Property
    private final String coverImage;
    private final List<String> genres;
    @Property
    private final String editor;
    @Property
    private final Short releaseYear;
    @Property
    private final LocalDateTime dateCreated;

    public Book(String title, String author, String coverImage, List<String> genres, String editor, Short releaseYear) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
        this.dateCreated = LocalDateTime.now();
    }

    public Long getBookId() {
        return bookId;
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
