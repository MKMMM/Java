package com.MKMMM.dependencyInjection.demo;

import com.MKMMM.dependencyInjection.demo.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// Initialize the Spring Application context with the configuration
		// defined in DemoApplication and command line arguments.
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		// Retrieve an instance of MyController from the application context.
		// This allows for dependency-injected behavior as defined in the Spring configuration.
		MyController controller = ctx.getBean(MyController.class);

		System.out.println("I'm in the main method!");
		System.out.println(controller.sayHello());

	}

}
