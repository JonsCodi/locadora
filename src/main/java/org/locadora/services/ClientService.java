
package org.locadora.services;

import org.locadora.domain.Client;
import org.locadora.repositories.ClientRepository;
import org.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository repo;

  public Client findByEmail(String email) {
    Client client = repo.findByEmail(email).orElseThrow(() ->
        new ObjectNotFoundException("Client with a "
            .concat(email)
            .concat(" as email not found! Type: ")
            .concat(Client.class.getName())));

    return client;
  }

  public void update(Client client) {
    repo.save(client);
  }
}
