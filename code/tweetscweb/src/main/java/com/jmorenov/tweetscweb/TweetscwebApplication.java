package com.jmorenov.tweetscweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class TweetscwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetscwebApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello Spring Boot!";
	}
}