/*********************************************************************************************************************
 *
 * COPYRIGHT 2017 TETRABIT DO BRASIL
 *
 * Terms of Use: This source code is licensed under the
 *
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International (CC BY-NC-ND 4.0) license,
 *
 * where a full copy of these terms follow this work in the LICENSE file and is also available at:
 *
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * You may not use this source code except in compliance with this License.
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 ********************************************************************************************************************/
package org.locadora.resources;

import com.google.gson.Gson;
import org.locadora.domain.Client;
import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.dto.ClientDTO;
import org.locadora.dto.MovieDTO;
import org.locadora.services.ClientService;
import org.locadora.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/client")
public class ClientResource {

  @Autowired
  private ClientService clientService;

  @Autowired
  private MovieService movieService;

  @PostMapping(value = "/add")
  public ResponseEntity<Object> add(@RequestBody ClientDTO clientDTO) {
    //TODO

    return ResponseEntity.ok().body("");
  }

  @PutMapping(value = "/i-want-some-movies")
  public ResponseEntity<Void> wantAMovie(@RequestBody String json) {
    ClientDTO clientDTO = new Gson().fromJson(json, ClientDTO.class);
    Client client = clientService.findByEmail(clientDTO.getEmail());

    for(MovieDTO movieDTO : clientDTO.getMovies()){
      Movie movie = movieService.findByDirectorAndTitleAndQuantityGreaterThan(movieDTO.getDirector(), movieDTO.getTitle(), 1);
      updateWhenIWantAMovie(client, movie);
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/my-movies")
  public ResponseEntity<List<MovieDTO>> findAll(@RequestParam("email") String email){
    Client client = clientService.findByEmail(email);
    List<MovieDTO> moviesDTO = new ArrayList<>();

    for(Movie movie : client.getMovies()){
      MovieDTO movieDTO = new MovieDTO(movie.getDirector(), movie.getTitle());
      moviesDTO.add(movieDTO);
    }

    return ResponseEntity.ok().body(moviesDTO);
  }

  @PutMapping(value = "/return-it")
  public ResponseEntity<Void> returnIt(@RequestBody String json){
    ClientDTO clientDTO = new Gson().fromJson(json, ClientDTO.class);
    Client client = clientService.findByEmail(clientDTO.getEmail());

    client.getMovies().forEach(movie -> updateWhenReturnIt(client, movie, clientDTO.getMovies()));

    return ResponseEntity.noContent().build();
  }

  private void updateWhenIWantAMovie(Client client, Movie movie) {
    client.getMovies().add(movie);

    movie.getClients().add(client);
    movie.setQuantity(movie.getQuantity()-1);

    if(movie.getQuantity() == 0) {
      movie.setStatus(MovieStatus.UNAVAILABLE.getCod());
    }

    movieService.update(movie);
    clientService.update(client);
  }

  private void updateWhenReturnIt(Client client, Movie movie, List<MovieDTO> movies) {
    for(MovieDTO movieDTO : movies){
      if(movieDTO.getTitle().equals(movie.getTitle()) &&
          movieDTO.getDirector().equals(movie.getDirector())){

        movie.setQuantity(movie.getQuantity()+1);
        client.getMovies().remove(movie);

        if(movie.getQuantity() == 1)
          movie.setStatus(MovieStatus.AVAILABLE.getCod());

        clientService.update(client);
        movieService.update(movie);
      }
    }

  }
}
