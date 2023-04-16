package project.library.model;

import java.time.LocalDateTime;

//@Entity(table="client")
public class Client {

    private long clientId;
    private final String userName;
    private final String email;
    private final String password;
    private final LocalDateTime dateCreated;

    public Client(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
