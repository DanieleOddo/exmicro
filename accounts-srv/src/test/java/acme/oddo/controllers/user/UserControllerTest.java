package acme.oddo.controllers.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
	private MockMvc mockMvc; 
    

    @Test
	public void itShouldBeBadRequest() throws Exception {
        this.mockMvc.perform(get("/V1/customerInfo/customer/{customerID}/account/{accountID}", 41, 1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());  
	}

    // Test to execute only with transactionSrv up & runnig
    /*
    @Test
	public void itShouldBeOK() throws Exception {
        this.mockMvc.perform(get("/V1/customerInfo/customer/{customerID}/account/{accountID}", 2, 1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());  
	}
     */
}
