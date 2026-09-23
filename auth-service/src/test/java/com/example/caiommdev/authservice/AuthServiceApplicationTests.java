package com.example.caiommdev.authservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"jwt.secret=test-secret-key-with-at-least-32-characters",
		"jwt.expiration-ms=86400000",
		"spring.cloud.config.enabled=false",
		"eureka.client.enabled=false"
})
class AuthServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
