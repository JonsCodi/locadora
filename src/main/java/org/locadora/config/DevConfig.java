
package org.locadora.config;

import org.locadora.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBService dbService;

  @Bean
  public boolean instantiateDatabase(){
    dbService.instantiateDatabase();
    return true;
  }

}
