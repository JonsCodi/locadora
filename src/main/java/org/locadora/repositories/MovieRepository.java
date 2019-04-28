package org.locadora.repositories;

import org.locadora.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

  @Transactional(readOnly = true)
  Optional<List<Movie>> findByStatus(Integer status);

  @Transactional(readOnly = true)
  Optional<Movie> findByDirector(String director);
}
