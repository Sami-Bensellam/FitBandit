package com.example.springbootfirstapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/name")
    public String name() {
        return "Nic";
    }

}
