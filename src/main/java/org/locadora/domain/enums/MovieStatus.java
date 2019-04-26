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
package org.locadora.domain.enums;

public enum MovieStatus {

  AVAILABLE(1, "Available"),
  UNAVAILABLE(1, "Unavailable");

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
