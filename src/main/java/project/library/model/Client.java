package project.library.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

@Node("client")
public class Client {

    @Id
    @GeneratedValue
    private long clientId;
    @Property
    private final String userName;
    @Property
    private final String email;
    @Property
    private final String password;
    @Property
    private final LocalDateTime dateCreated;

    public Client(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
    }

    public long getClientId() {
        return clientId;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
