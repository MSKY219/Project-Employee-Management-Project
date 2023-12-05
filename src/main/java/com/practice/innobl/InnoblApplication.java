package com.practice.innobl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InnoblApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnoblApplication.class, args);
	}

}
