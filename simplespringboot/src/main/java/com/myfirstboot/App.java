package com.myfirstboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * Note that a WebApplicationInitializer is only needed if you are building a
 * war file and deploying it. If you prefer to run an embedded web server then
 * you won't need this at all.
 */

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}