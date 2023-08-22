package com.MKMMM.szkolenia.demo.config;

import com.MKMMM.szkolenia.demo.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyAppConfig {
    @Bean
    @Scope("prototype")
    public Person createMan(){
        System.out.println("Bean Created! createMan method");
        return new Person("Jan", "2001");

    }


    // Adding scope, adding Qualifier,
    @Bean
    @Scope("singleton")
    @Qualifier("paniJanina")
    public Person createWoman(){
        System.out.println("Bean Created! createWoman method");
        return new Person("Janina", "2000");

    }

}
