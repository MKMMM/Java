package com.MKMMM.dependencyInjection.demo.controllers;

import org.springframework.stereotype.Controller;
import com.MKMMM.dependencyInjection.demo.services.GreetingService;
import com.MKMMM.dependencyInjection.demo.services.GreetingServiceImpl;

@Controller
public class MyController {

    private final GreetingService greetingService;
    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }


    public String sayHello(){
        System.out.println("I'm in the Main controller");
        return greetingService.sayGreeting();
    }

}
