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
package org.locadora.services;

import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.repositories.MovieRepository;
import org.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

  @Autowired
  private MovieRepository repo;

  public List<Movie> findByStatus(int status) {
    return repo.findByStatus(status)
        .orElseThrow(() ->
            new ObjectNotFoundException("Movie with status "
                .concat(MovieStatus.toEnum(status).getDescription())
                .concat(" as director not found! Class Type: ")
                .concat(Movie.class.getName())));
  }

  public List<Movie> findByTitle(String title) {
    Optional<List<Movie>> movie = repo.findByTitle(title);
    return movie
        .orElseThrow(() ->
            new ObjectNotFoundException("Movie with a "
                .concat(title)
                .concat(" as title not found! Class Type: ")
                .concat(Movie.class.getName())));
  }

  public List<Movie> findByDirector(String director) {
    Optional<List<Movie>> movie = repo.findByDirector(director);
    return movie
        .orElseThrow(() ->
            new ObjectNotFoundException("Movie with a "
                .concat(director)
                .concat(" as director not found! Class Type: ")
                .concat(Movie.class.getName())));
  }

  public Movie findByDirectorAndTitleAndQuantityGreaterThan(String director, String title, int quantity) {
    Optional<Movie> movie = repo.findByDirectorAndTitleAndQuantityGreaterThanEqual(director, title, quantity);
    return movie
        .orElseThrow(() ->
            new ObjectNotFoundException("Movie with a "
                .concat(director)
                .concat(" as director and ")
                .concat(title)
                .concat(" as title not found! Class Type: ")
                .concat(Movie.class.getName())));
  }

  public void update(Movie movie) {
    repo.save(movie);
  }

}


