package com.mkpits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MkpitsTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkpitsTaskApplication.class, args);
	}

}
