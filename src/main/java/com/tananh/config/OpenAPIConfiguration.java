package com.tananh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       Server server = new Server();
       server.setUrl("http://localhost:5454");
       server.setDescription("Ứng dụng Social Media");

       Contact myContact = new Contact();
       myContact.setName("Ho Tan Anh");
       myContact.setEmail("ann152k3@gmail.com");

       Info information = new Info()
               .title("SocialMedia System API")
               .version("1.0")
               .description("This API exposes endpoints to manage application.")
               .contact(myContact);
       
      
       SecurityScheme securityScheme = new SecurityScheme()
    		   .name("bearerAuth")
               .type(SecurityScheme.Type.HTTP)
               .scheme("bearer")
               .bearerFormat("JWT")
               .in(SecurityScheme.In.HEADER);

       
       Components components = new Components().addSecuritySchemes("bearerAuth", securityScheme);

       return new OpenAPI().info(information).servers(List.of(server)).components(components);
   }
}
