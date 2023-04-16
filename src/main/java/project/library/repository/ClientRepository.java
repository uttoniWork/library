package project.library.repository;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import project.library.model.Client;

@Repository
public interface ClientRepository extends ReactiveNeo4jRepository<Client, Long> {

}
