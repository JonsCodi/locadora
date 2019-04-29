
package org.locadora.services;

import org.locadora.domain.Client;
import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.repositories.ClientRepository;
import org.locadora.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private MovieRepository movieRepository;

  public void instantiateDatabase(){

    clientRepository.deleteAllInBatch();
    movieRepository.deleteAllInBatch();

    Client sean = new Client("Sean", "sean@gmail.com", "admin");
    Client ham = new Client("Ham", "ham@hotmail.com", "admin");
    Client xen = new Client("Xen", "xen@hotmail.com", "admin");
    Client usuario_1 = new Client("user1", "user1@hotmail.com", "admin");
    Client usuario_2 = new Client("user2", "user2@hotmail.com", "admin");
    Client usuario_3 = new Client("user3", "user3@hotmail.com", "admin");

    Movie django = new Movie("Django", "Tarantino", 5, MovieStatus.AVAILABLE);
    Movie intoWild = new Movie("Into the Wild", "Sean Penn", 5,MovieStatus.AVAILABLE);
    Movie whatchmen = new Movie("Whatchmen", "Zack Snyder", 5, MovieStatus.AVAILABLE);
    Movie coherence = new Movie("Coherence", "James Ward", 5, MovieStatus.AVAILABLE);
    Movie ex_Machina = new Movie("Ex Machina", "Alex Garland", 5,MovieStatus.AVAILABLE);
    Movie annihilation = new Movie("Annihilation", "Alex Garland", 5, MovieStatus.AVAILABLE);


    List<Movie> movies = Arrays.asList(django, intoWild, whatchmen, coherence, ex_Machina, annihilation);
    List<Client> clients = Arrays.asList(sean, ham, xen, usuario_1, usuario_2, usuario_3);

    clientRepository.saveAll(clients);
    movieRepository.saveAll(movies);

  }

  private void addClientAndMovies(List<Client> clients, List<Movie> movies, int index, String director) {
    clients.get(index)
        .setMovies(
            movies.stream()
            .filter(movie -> movie.getDirector().equals(director))
            .filter(movie -> movie.getStatus().equals(MovieStatus.AVAILABLE.getCod()))
            .filter(movie -> movie.getQuantity() > 0)
            .collect(Collectors.toSet()));

    movies.get(index).setClients(new HashSet<>(clients));

  }
}
