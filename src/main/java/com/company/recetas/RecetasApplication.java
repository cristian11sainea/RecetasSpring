package com.company.recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RecetasApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "cris1234";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("contrasena:" +encodedPassword);
		SpringApplication.run(RecetasApplication.class, args);
	}

}
