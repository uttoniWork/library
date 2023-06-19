package project.library.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.exception.ClientAlreadyExistsException;
import project.library.exception.ClientNotExistException;
import project.library.model.Client;
import project.library.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(ClientRequest clientRequest) {

        checkClientAlreadyExists(clientRequest.getEmail(), clientRequest.getPassword(), clientRequest.getUserName());

        final BCryptPasswordEncoder cryptographic = new BCryptPasswordEncoder(12);
        final String encodedPassword = cryptographic.encode(clientRequest.getPassword());

        return clientRepository.save(new Client(clientRequest.getUserName(), clientRequest.getEmail(), encodedPassword));
    }

    public ClientLoginResponse loginClient(ClientLoginRequest clientLoginRequest) {


        System.out.println("Client email: " + clientLoginRequest.getEmail());
        System.out.println("Client pwd: " + clientLoginRequest.getPassword());

        final Client clientFound = checkClientExists(clientLoginRequest.getEmail(), clientLoginRequest.getPassword());

        System.out.println("Cliente encontrado!");

        return new ClientLoginResponse(clientFound.getClientId(), clientFound.getUserName());
    }

    public Client findClient(Long clientId){
        return clientRepository.findById(clientId).get();
    }

    private Client checkClientExists(String email, String password){
        final Optional<Client> client = clientRepository.findByEmail(email);

        if(client.isEmpty()){
            new ClientNotExistException("Cliente não existe, corrija email e/ou senha!");
        }

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        if(encoder.matches(password, client.get().getPassword())){
            return client.get();
        }

        throw new ClientNotExistException("Cliente não existe, corrija email e/ou senha!");
    }

    private void checkClientAlreadyExists(String email, String password, String userName){
        if(clientRepository.findByEmailAndPasswordAndUserName(email, password, userName).isPresent())
            throw new ClientAlreadyExistsException("Cliente já cadastrado!");
    }
}