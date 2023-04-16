package project.library.repository;

import org.example.model.Client;

//@Repository
//public interface ClientRepository extends JPARepository<Client, Long>{
public interface ClientRepository {

    Client save(Client client);
}
