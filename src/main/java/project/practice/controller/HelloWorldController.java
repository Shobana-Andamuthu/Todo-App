package project.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/h")
    String sayHelloWorld()
    {
        return "Hello World!!";
    }

}
