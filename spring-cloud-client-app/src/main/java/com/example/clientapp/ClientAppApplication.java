package com.example.clientapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClientAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAppApplication.class, args);
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();

        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setAccessTokenUri("http://localhost:9000/services/oauth/token");
        details.setClientId("client-id");
        details.setClientSecret("client-secret");
        details.setScope(Arrays.asList("toll_read"));

        details.setUsername("admin");
        details.setPassword("admin");

        return new OAuth2RestTemplate(details);
    }

    @Bean
    public CommandLineRunner commandLineRunner(OAuth2RestTemplate restTemplate) {
        return args -> {
            System.out.println("Access Token: " + restTemplate.getAccessToken().toString());
            List response = restTemplate.getForObject("http://localhost:9001/resources/houses", List.class);
            System.out.println("Response: " + response);
        };
    }
}
