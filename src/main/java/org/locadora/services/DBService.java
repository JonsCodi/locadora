
package org.locadora.services;

import org.locadora.domain.Client;
import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.repositories.ClientRepository;
import org.locadora.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  public void instantiateTestDatabase(){

    clientRepository.deleteAllInBatch();
    movieRepository.deleteAllInBatch();

    Client sean = new Client("Sean", "sean@gmail.com", "admin");
    Client ham = new Client("Ham", "ham@hotmail.com", "admin");
    Client xen = new Client("Xen", "xen@hotmail.com", "admin");

    Movie django = new Movie("Django", "Tarantino", 0, MovieStatus.UNAVAILABLE);
    Movie wild = new Movie("Into the Wild", "Sean Penn", 1,MovieStatus.AVAILABLE);
    Movie dogs = new Movie("Whatchmen", "Zack Snyder", 12, MovieStatus.AVAILABLE);

    List<Movie> movies = Arrays.asList(django, wild, dogs);
    List<Client> clients = Arrays.asList(sean, ham, xen);

    clientRepository.saveAll(clients);
    movieRepository.saveAll(movies);

    addClientAndMovies(clients, movies, 0, "Tarantino");

    addClientAndMovies(clients, movies, 1, "Sean Penn");

    addClientAndMovies(clients, movies, 2, "Zack Snyder");

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
