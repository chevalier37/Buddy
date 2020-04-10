package com.buddy;


import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties
@EnableJpaRepositories
public class BuddyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BuddyApplication.class, args);
	}
	
	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	

}
