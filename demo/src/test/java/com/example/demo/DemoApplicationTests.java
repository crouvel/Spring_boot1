package com.example.demo;

import com.example.demo.controller.EmployeurController;
import com.example.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private UserController controller;
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}

