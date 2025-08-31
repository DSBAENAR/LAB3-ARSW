package edu.eci.arsw.blueprints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppCoreApplication {

	public static void main(String[] args) {
		SpringVersion.run(AppCoreApplication.class, args);
	}

}
