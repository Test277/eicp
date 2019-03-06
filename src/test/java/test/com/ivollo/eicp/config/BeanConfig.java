package test.com.ivollo.eicp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class BeanConfig {
    @Bean
    public WebTestClient webTestClient(){
        WebTestClient webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://192.168.22.61:8897")
                .build();
        return webTestClient;
    }
}
