package project.library.service;

import org.example.dto.ClientLoginRequest;
import org.example.dto.ClientRequest;
import org.example.model.Client;
import org.example.repository.ClientRepository;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(ClientRequest clientRequest) {
        final Client client = new Client(clientRequest.getUserName(), clientRequest.getEmail(), clientRequest.getPassword());

        return clientRepository.save(client);
    }

    public Long loginClient(ClientLoginRequest clientLoginRequest) {
        final Long clientId = clientRepositoryfindClientClientIdByClientEmailAndPassword(clientRepository

                S
        );
    }
}
