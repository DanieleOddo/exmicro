package acme.oddo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import acme.oddo.controllers.account.CreateAccountController;

@SpringBootTest
public class SmokeTest {

    @Autowired
	private CreateAccountController createAccountController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(createAccountController).isNotNull();
	}
    
}
