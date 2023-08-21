package com.MKMMM.dependencyInjection.demo.controllers;

import org.springframework.stereotype.Controller;
import com.MKMMM.dependencyInjection.demo.services.GreetingService;

@Controller
public class ConstructorInjectedController {
    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    private final GreetingService greetingService;

    public String sayHellO(){
        System.out.println("ConstructorInjectedController");
        return greetingService.sayGreeting();
    }

}
