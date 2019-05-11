package com.java.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.java.dao.UserRepository;

@SpringBootApplication(scanBasePackages={"com.java.dao","com.java.rest","com.java.service",
		"com.java.config","com.java.model"})
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
public class RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}

}

