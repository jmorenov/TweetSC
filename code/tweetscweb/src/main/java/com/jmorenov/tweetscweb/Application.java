package com.jmorenov.tweetscweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
@SpringBootApplication
public class Application {
    /**
     * Main method of the web application.
     * @param args String[] with the arguments of the execution.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}