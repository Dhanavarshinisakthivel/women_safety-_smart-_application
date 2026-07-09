package com.womensafety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the "front door" of our whole backend.
 * When you run this file, Spring Boot starts up a small web server
 * (like a mini version of what google.com runs on) on your computer.
 *
 * Think of Spring Boot as a restaurant kitchen manager:
 * - It hires the staff (creates all our Controller/Service/Repository objects)
 * - It makes sure everyone has what they need (dependency injection)
 * - It opens the doors for customers (starts listening for web requests)
 */
@SpringBootApplication
public class WomenSafetyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WomenSafetyApplication.class, args);
        System.out.println("=================================================");
        System.out.println(" Women Safety App backend is running!");
        System.out.println(" Open http://localhost:8080 in your browser");
        System.out.println("=================================================");
    }
}
