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
import com.google.gson.JsonObject;
import org.locadora.domain.Client;
import org.locadora.dto.ClientDTO;
import org.locadora.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/client")
public class ClientResource {

  @Autowired
  private ClientService service;

  @PostMapping(value = "/add")
  public ResponseEntity<Object> add(@RequestBody ClientDTO clientDTO) {
    //TODO

    return ResponseEntity.ok().body("");
  }

  @PostMapping(value = "/i-want-a-movie")
  public ResponseEntity<Object> wantAMovie(@RequestBody String json) {
    //TODO
    JsonObject jsonObject = new Gson().toJsonTree(json).getAsJsonObject();
    String email = jsonObject.get("email").toString();
    String movieName = jsonObject.get("movie_name").toString();

    return ResponseEntity.ok().body("");
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<Client> findAll(){
    List<Client> clients = service.findAll();
    return ResponseEntity.ok().body(clients.stream().findFirst().get());
  }




}
