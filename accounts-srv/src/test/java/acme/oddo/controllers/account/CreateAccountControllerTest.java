package acme.oddo.controllers.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateAccountControllerTest {

	@Autowired
	private MockMvc mockMvc; 
    
	@Test
	public void itShouldBeBadRequest() throws Exception {
		this.mockMvc.perform(post("/V1/newAccount/customer/{customerID}/initCredit/{initialCredit}", 10, "12.34")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	// Test Valid only if Both Services (accountSrv & transactionSrv) are runnig
	/*
	@Test
	public void itShouldBeCreateNewAccount() throws Exception {
		this.mockMvc.perform(post("/V1/newAccount/customer/{customerID}/initCredit/{initCredit}", 2, "99")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	 */
}