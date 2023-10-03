package com.guilherme.delfino.pubsub.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void generatePassword(){

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		System.out.println(passwordEncoder.encode("senha"));

		// insert into application_user(username, password, roles) values ('guilherme', '$2a$10$Mhp9NNMtk/9.yLeRf0lRDuokb5kZafvNLqrzWDjjnCkVYsDQKREDW', 'ADMIN')
	}

}
