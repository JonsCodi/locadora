package org.locadora.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
public class Client implements Serializable {

  private static final Long SerialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  @Column(unique = true)
  @NaturalId
  private String email;

  @Getter
  @Setter
  private String password;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "client_movie",
      joinColumns = {@JoinColumn(name = "client_id")},
      inverseJoinColumns = {@JoinColumn(name = "movie_id")})
  @Getter
  @Setter
  private Set<Movie> movies = new HashSet<>();

  public Client(String name, String email, String password){
    this.name = name;
    this.email = email;
    this.password = password;
  }

}
