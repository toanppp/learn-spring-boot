package com.example.learnspringboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, String> users = new HashMap<>();

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(name = "name") String name) {
        var greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        users.put(greeting.id(), greeting.content());
        return ResponseEntity.ok(greeting);
    }

    @GetMapping("/greeting/{id}")
    public ResponseEntity<Greeting> find(@PathVariable(value = "id") Long id) {
        if (!users.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new Greeting(id, users.get(id)));
    }
}
