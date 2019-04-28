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

import org.locadora.domain.Movie;
import org.locadora.domain.enums.MovieStatus;
import org.locadora.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/movie")
public class MovieResource {

  @Autowired
  private MovieService service;

  @GetMapping(value="/findByStatus")
  public ResponseEntity<List<Movie>> findByStatus(@RequestParam(value="status") String status) {
    List<Movie> movies = service.findByStatus(MovieStatus.valueOf(status.toUpperCase()).getCod());

    return ResponseEntity.ok().body(movies);
  }


  @GetMapping(value="/findByDirector")
  public ResponseEntity<Movie> findByDirector(@RequestParam(value="director") String director){
    return ResponseEntity.ok().body(service.findByDirector(director));
  }


}
