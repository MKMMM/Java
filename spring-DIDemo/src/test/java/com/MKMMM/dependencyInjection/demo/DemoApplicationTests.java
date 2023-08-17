package com.MKMMM.dependencyInjection.demo;

import com.MKMMM.dependencyInjection.demo.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {

	// Injecting the Spring Application context to be used in our tests.
	@Autowired
	ApplicationContext appContext;

	// Injecting an instance of MyController for testing.
	@Autowired
	MyController myController;

	// This test validates that the MyController bean has been properly autowired.
	@Test
	void testAutowireOfController() {
		System.out.println("Test Autowired");
		System.out.println(myController.sayHello());
	}

	// This test retrieves the MyController bean from the application context and validates its behavior.
	@Test
	void testGetControllerFrommCtx() {
		MyController myController = appContext.getBean(MyController.class);
		System.out.println("Test controller:");
		System.out.println(myController.sayHello());
	}

	@Test
	void contextLoads() {
	}

}
