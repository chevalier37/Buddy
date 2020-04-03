package com.buddy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootTest
@EnableEncryptableProperties
@PropertySource(name="EncryptedProperties", value = "classpath:application.properties")
class BuddyApplicationTests {

	@Test
	void contextLoads() {
	}

}
