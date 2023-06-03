package project.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import project.library.dto.request.ClientLoginRequest;
import project.library.dto.request.ClientRequest;
import project.library.dto.response.ClientLoginResponse;
import project.library.model.Client;
import project.library.service.ClientService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    private static final Long CLIENT_ID = 1L;
    private static final String USERNAME = "Harry";
    private static final String PASSWORD = "12345";
    private static final String EMAIL = "harry@gmail.com";

    private final ClientService clientService = mock(ClientService.class);
    private final ClientController clientController = new ClientController(clientService);

    @Test
    public void shouldPostClient() {
        when(clientService.saveClient(any(ClientRequest.class))).thenReturn(getClient());

        final ResponseEntity<Client> actualClientResponse = clientController.postClient(getClientRequest());
        final ResponseEntity<Client> expectedClientResponse = getResponseEntityClient(getClient());

        assertThat(actualClientResponse.getBody()).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(expectedClientResponse.getBody());
        assertEquals(expectedClientResponse.getStatusCode(), actualClientResponse.getStatusCode());
        verify(clientService, times(1)).saveClient(any(ClientRequest.class));
    }

    @Test
    public void shouldLoginClient() {
        when(clientService.loginClient(any(ClientLoginRequest.class))).thenReturn(getClientLoginResponse());

        final ResponseEntity<ClientLoginResponse> actualClientResponse = clientController.loginClient(EMAIL, PASSWORD);
        final ResponseEntity<ClientLoginResponse> expectedClientResponse = getResponseEntityClientLogin(getClientLoginResponse());

        assertThat(actualClientResponse.getBody()).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(expectedClientResponse.getBody());
        assertEquals(expectedClientResponse.getStatusCode(), actualClientResponse.getStatusCode());
        verify(clientService, times(1)).loginClient(any(ClientLoginRequest.class));
    }

    private ClientRequest getClientRequest() {
        return new ClientRequest(USERNAME, EMAIL, PASSWORD);
    }

    private Client getClient() {
        final Client client = new Client(USERNAME, EMAIL, PASSWORD);
        client.setClientId(CLIENT_ID);

        return client;
    }

    private ResponseEntity<Client> getResponseEntityClient(Client client) {
        return ResponseEntity.ok(client);
    }

    private ResponseEntity<ClientLoginResponse> getResponseEntityClientLogin(ClientLoginResponse clientLoginResponse) {
        return ResponseEntity.ok(clientLoginResponse);
    }

    private ClientLoginResponse getClientLoginResponse() {
        return new ClientLoginResponse(CLIENT_ID, USERNAME);
    }
}
