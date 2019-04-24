
package org.locadora.services;

import org.locadora.domain.Cliente;
import org.locadora.domain.Filme;
import org.locadora.repositories.ClienteRepository;
import org.locadora.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private FilmeRepository filmeRepository;

  public void instantiateTestDatabase(){
    Cliente cliente_1= new Cliente("Jonas");
    Filme filme_1 = new Filme("Django");

    Cliente cliente_2= new Cliente("Lucas");
    Filme filme_2 = new Filme("Caes de Aluguel");

    Cliente cliente_3= new Cliente("Lucas");
    Filme filme_3 = new Filme("Into the Wild");


  }
}
