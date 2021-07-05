package com.backend.demo.rest_controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo_rest_api")
public class DemoRestController {
    @GetMapping
    private String getDemo() {
        return "hello world!!!";
    }
}
