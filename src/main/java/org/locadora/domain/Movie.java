package org.locadora.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locadora.domain.enums.MovieStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Entity
public class Movie implements Serializable {

  private static final Long SerialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;

  @Getter
  @Setter
  private String title;

  @Getter
  @Setter
  private String director;

  @Getter
  @Setter
  private Integer quantity;

  @Getter
  @Setter
  private Integer status;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      mappedBy = "movies")
  @Getter
  @Setter
  private Set<Client> clients = new HashSet<>();

  public Movie(String title, String director, Integer quantity, MovieStatus status) {
    this.title = title;
    this.director = director;
    this.quantity = quantity;
    this.status = (Objects.isNull(status)) ? null : status.getCod();
  }

}
