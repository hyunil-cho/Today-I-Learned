package com.crud.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RepositioryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepositioryApplication.class, args);
	}

}
