package com.jmorenov.tweetscweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@SpringBootApplication
@RestController
public class TweetscWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetscWebApplication.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		return "Hello Spring Boot, I'm on App Engine!!!";
	}

    @GetMapping("/time")
    public String time() {
        return Calendar.getInstance().getTime().toString();
    }
}