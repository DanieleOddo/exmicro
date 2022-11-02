package acme.oddo.controllers.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import acme.oddo.controllers.account.dto.RequestAccountDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateAccountControllerTest {

	@Autowired
	private MockMvc mockMvc; 
    
	@Test
	public void itShouldBeBadRequest() throws Exception {
		RequestAccountDTO reqs = new RequestAccountDTO(); 
		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(post("/newAccount")
			.param("input", mapper.writeValueAsString(reqs))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void itShouldBeCreateNewAccount() throws Exception {
		RequestAccountDTO reqs = new RequestAccountDTO();
		reqs.setCustomerID(5);
		reqs.setInitialCredit(Double.parseDouble("11.00"));
		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(post("/newAccount")
			.param("input", mapper.writeValueAsString(reqs))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}


}