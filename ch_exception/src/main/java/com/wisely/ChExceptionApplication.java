package com.wisely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude=ErrorMvcAutoConfiguration.class)
public class ChExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChExceptionApplication.class, args);
	}
}
