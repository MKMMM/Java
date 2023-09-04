package com.MKMMM.szkolenia.demo.service;

import com.MKMMM.szkolenia.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PersonManager {
    private Person director;
    public PersonManager(@Qualifier("paniJanina") Person director) {

        this.director = director;
        System.out.println("Creating PersonManager object");

    }

}
