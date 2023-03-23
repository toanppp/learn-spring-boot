package com.example.learnspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(name = "name") String name) {
        var greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        return ResponseEntity.ok(greeting);
    }
}
