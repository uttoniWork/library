package project.library.service;

import project.library.dto.ClientRequest;
import project.library.model.Client;
import project.library.repository.ClientRepository;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(ClientRequest clientRequest) {
        final Client client = new Client(clientRequest.getUserName(), clientRequest.getEmail(), clientRequest.getPassword());

        return clientRepository.save(client);
    }
}
