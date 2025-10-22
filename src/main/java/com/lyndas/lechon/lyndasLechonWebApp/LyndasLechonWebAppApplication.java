package com.lyndas.lechon.lyndasLechonWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.lyndas.lechon")
@EntityScan("com.lyndas.lechon.model")
@EnableJpaRepositories("com.lyndas.lechon.repository")
public class LyndasLechonWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyndasLechonWebAppApplication.class, args);
	}

}
