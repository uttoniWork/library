package project.library.model;

import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;

@Node("book")
public class Book {

    @Id
    @GeneratedValue
    private Long bookId;
    @Property
    private String title;
    @Property
    private String author;
    @Property
    private String coverImage;
    @Relationship(type = "EH_DO_GENERO", direction = Relationship.Direction.OUTGOING)
    private List<Genre> genres;
    @Property
    private String editor;
    @Property
    private Short releaseYear;
    @Property
    private LocalDateTime dateCreated;
    @Relationship(type = "GOSTA_DO_LIVRO", direction = Relationship.Direction.INCOMING)
    private List<Client> clientList;

    public Book() {}

    public Book(String title, String author, String coverImage, List<Genre> genres, String editor, Short releaseYear, List<Client> clientList) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
        this.genres = genres;
        this.editor = editor;
        this.releaseYear = releaseYear;
        this.clientList = clientList;
        this.dateCreated = LocalDateTime.now();
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (coverImage != null ? !coverImage.equals(book.coverImage) : book.coverImage != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        if (editor != null ? !editor.equals(book.editor) : book.editor != null) return false;
        if (releaseYear != null ? !releaseYear.equals(book.releaseYear) : book.releaseYear != null) return false;
        return clientList != null ? clientList.equals(book.clientList) : book.clientList == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (coverImage != null ? coverImage.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (clientList != null ? clientList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", genres=" + genres +
                ", editor='" + editor + '\'' +
                ", releaseYear=" + releaseYear +
                ", dateCreated=" + dateCreated +
                ", clientList=" + clientList +
                '}';
    }
}
