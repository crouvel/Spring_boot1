package com.example.demo;
import com.example.demo.controller.EmployeurController;
import com.example.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMesssage() throws  Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:8081/api/v1/users", String.class)).contains("clarence.rouvel@gmail.com");
        assertThat(this.restTemplate.getForObject("http://localhost:8081/api/v1/users/employeurs", String.class)).contains("employeur.employeur@gmail.com");
        assertThat(this.restTemplate.getForObject("http://localhost:8081/api/v1/users/chercheurs", String.class)).contains("employeur2.employeur@gmail.com");

    }
}
