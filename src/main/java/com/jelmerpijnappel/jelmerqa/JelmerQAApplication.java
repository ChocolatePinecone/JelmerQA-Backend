package com.jelmerpijnappel.jelmerqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * An API used for storing, retrieving and modifying question data of questions sent to Jelmer Pijnappel through <a href="http://jelmerpijnappel.com">http://jelmerpijnappel.com</a>
 */
@SpringBootApplication
public class JelmerQAApplication {

	public static void main(String[] args) {
		SpringApplication.run(JelmerQAApplication.class, args);
	}

}
