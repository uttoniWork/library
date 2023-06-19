package project.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByEmailAndPasswordAndUserName(String email, String password, String userName);
}
