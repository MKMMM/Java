package com.MKMMM.szkolenia.demo;
import com.MKMMM.szkolenia.demo.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class DemoApplication {

	public static void main(String[] args) {

		// Assign Application context to the main method
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		// Get the BEAN!!!
		Person person1 = applicationContext.getBean("createMan", Person.class);
		Person person2 = applicationContext.getBean("createWoman", Person.class);

		System.out.println(person1);
		System.out.println(person2);

		Person andrzej = applicationContext.getBean("andrzej", Person.class);
		System.out.println(andrzej);

	}

}
