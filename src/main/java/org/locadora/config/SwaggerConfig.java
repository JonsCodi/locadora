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
package org.locadora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private final ResponseMessage m204 = SimpleMessage(204, "Update ok");
  private final ResponseMessage m404 = SimpleMessage(404, "Not Found");
  private final ResponseMessage m500 = SimpleMessage(500, "Server Error");

  private ResponseMessage SimpleMessage(int code, String msg) {
    return new ResponseMessageBuilder().code(code).message(msg).build();
  }

  @Bean
  public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
               .useDefaultResponseMessages(false)
               .globalResponseMessage(RequestMethod.GET, Arrays.asList(m404, m500))
               .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204, m404, m500))
               .select()
               .apis(RequestHandlerSelectors.basePackage("org.locadora.resources"))
               .paths(PathSelectors.any())
               .build()
               .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo(){
    return new ApiInfo("Locadora 4all",
              "API REST para integracao com a Locadora 4all",
              "1.0.0",
              "No Terms",
              new Contact("Jonas Gomes", "https://github.com/JonsCodi/", "jonasgoms@hotmail.com"),
              "No License",
              "No License Url",
              Collections.emptyList());
  }
}
