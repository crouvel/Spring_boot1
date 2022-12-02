package com.example.demo;

import com.example.demo.controller.EmployeurController;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
//import com.example.demo.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest(UserController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;

    /*@Autowired
    private UserDetailsServiceImpl service;*/

    @MockBean
    private UserRepository controller;



    /*@Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.loadUserByUsername()).thenReturn("Hello, Mock");
        this.mockMvc.perform(get("/greeting")).andDo(print())
        // Verfication du header
                .andExpect(status().isOk())
        // Verification du contenu
                .andExpect(content()
                        .string(containsString("Hello, Mock")));
    }*/

    @Test
    @DisplayName("GET /users/1 - Found")
    void testUserById() throws Exception{
        User mockUser = new User(111L,"test@gmail.com", "123456");
        doReturn(Optional.of(mockUser)).when(controller).findById(111L);

        mockMvc.perform(get("/api/v1/users/{id}", 111))
                .andExpect(status().isOk());

    }
}