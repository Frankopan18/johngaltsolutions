package com.johngalt.solutions.first.task.frankopan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SolutionForApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionForApiApplication.class, args);
	}

}
