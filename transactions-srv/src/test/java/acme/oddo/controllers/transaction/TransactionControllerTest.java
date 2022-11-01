package acme.oddo.controllers.transaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        request.setImpValue(Double.parseDouble("10.00"));
        ObjectMapper mapper = new ObjectMapper();

        this.mvc.perform(put("/newTransaction")
            .param("input", mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    void itShouldBeBadRequest() throws Exception {

        RequestTransactionDTO request = new RequestTransactionDTO();
        request.setImpValue(Double.parseDouble("10.00"));
        ObjectMapper mapper = new ObjectMapper();

        this.mvc.perform(put("/newTransaction")
            .param("input", mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
