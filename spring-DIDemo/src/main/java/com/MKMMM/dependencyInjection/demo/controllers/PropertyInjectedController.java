package com.MKMMM.dependencyInjection.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.MKMMM.dependencyInjection.demo.services.GreetingService;

@Controller
public class PropertyInjectedController {

    @Autowired
    GreetingService greetingService;

    public String sayHello(){
        System.out.println("PropertyInjectedController");
        return greetingService.sayGreeting();
    }



}
