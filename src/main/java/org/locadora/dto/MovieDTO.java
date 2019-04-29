
package org.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String director;

  private String title;


}
