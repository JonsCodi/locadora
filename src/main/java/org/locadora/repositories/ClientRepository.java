package org.locadora.repositories;

import org.locadora.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

  @Transactional(readOnly = true)
  Optional<Client> findByEmail(String email);


}
