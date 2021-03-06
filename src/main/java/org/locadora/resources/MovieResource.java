
package org.locadora.resources;

import io.swagger.annotations.ApiOperation;
import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.dto.MovieDTO;
import org.locadora.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/movie")
public class MovieResource {

  @Autowired
  private MovieService service;

  @ApiOperation(value = "Busca todos os filmes correspondente do status. Apenas dois parametros: 1) Available 2) Unavailable")
  @GetMapping(value="/findByStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<MovieDTO>> findByStatus(@RequestParam(value="status") String status) {
    List<Movie> movies = service.findByStatus(MovieStatus.valueOf(status.toUpperCase()).getCod());
    List<MovieDTO> movieDTOS = movieToMovieDTO(movies);

    return ResponseEntity.ok().body(movieDTOS);
  }

  @ApiOperation(value = "Busca todos os filmes correspondente ao Diretor.")
  @GetMapping(value="/findByDirector", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<MovieDTO>> findByDirector(@RequestParam(value="director") String director){
    List<Movie> movies = service.findByDirector(director);
    List<MovieDTO> movieDTOS = movieToMovieDTO(movies);

    return ResponseEntity.ok().body(movieDTOS);
  }

  @ApiOperation(value = "Busca todos os filmes correspondente ao Titulo.")
  @GetMapping(value= "/findByTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<MovieDTO>> findByTitle(@RequestParam(value="title") String title){
    List<Movie> movies = service.findByTitle(title);
    List<MovieDTO> movieDTOS = movieToMovieDTO(movies);

    return ResponseEntity.ok().body(movieDTOS);
  }

  private List<MovieDTO> movieToMovieDTO(List<Movie> movies) {
    List<MovieDTO> movieDTOS = new ArrayList<>();

    movies.forEach(movie -> movieDTOS.add(new MovieDTO(movie.getTitle(), movie.getDirector())));
    return movieDTOS;
  }


}
