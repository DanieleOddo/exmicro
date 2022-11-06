package acme.oddo.controllers.transaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

   
    @Autowired
    private MockMvc mvc;


    @Test
    void putTransacion () throws Exception {

        RequestTransactionDTO request = new RequestTransactionDTO();
        request.setAccountID(1);
        request.setCustomerID(2);
        request.setImpValue(10.00);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestStr = mapper.writeValueAsString(request); 
        this.mvc.perform(post("/V1/newTransaction")
            .content(requestStr)
            .contentType(MediaType.APPLICATION_JSON))            
            .andExpect(status().isOk());

    }

    @Test
    void itShouldBeBadRequest() throws Exception {

        RequestTransactionDTO request = new RequestTransactionDTO();
        request.setAccountID(2);
        request.setImpValue(10.00);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String requestStr = mapper.writeValueAsString(request); 
        this.mvc.perform(post("/V1/newTransaction")
            .content(requestStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
