
package org.locadora.domain.enums;

public enum MovieStatus {

  AVAILABLE(1, "Available"),
  UNAVAILABLE(2, "Unavailable");

  private int cod;
  private String description;

  MovieStatus(int cod, String description) {
    this.cod = cod;
    this.description = description;
  }

  public int getCod() {
    return cod;
  }

  public String getDescription() {
    return description;
  }

  public static MovieStatus toEnum(Integer cod) {

    if (cod == null) {
      return null;
    }

    for (MovieStatus x : MovieStatus.values()) {
      if (cod.equals(x.getCod())) {
        return x;
      }
    }

    throw new IllegalArgumentException("Invalid Id: " + cod);
  }


}
