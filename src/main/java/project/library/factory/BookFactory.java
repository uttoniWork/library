package project.library.factory;

import org.springframework.stereotype.Component;
import project.library.dto.request.BookRegistrationRequest;
import project.library.model.Book;
import project.library.model.Client;
import project.library.model.Genre;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookFactory {

    public Book getBook(BookRegistrationRequest bookRegistrationRequest, List<Genre> genres, Client client){

        final List<Client> clients = new ArrayList<>();
        clients.add(client);

        return new Book(
                bookRegistrationRequest.getTitle(),
                bookRegistrationRequest.getAuthor(),
                bookRegistrationRequest.getCoverImage(),
                genres,
                bookRegistrationRequest.getEditor(),
                bookRegistrationRequest.getReleaseYear(),
                clients
        );
    }
}
