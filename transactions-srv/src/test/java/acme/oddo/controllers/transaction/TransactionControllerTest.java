package acme.oddo.controllers.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.IntegerAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shoudReturnStatusOk () throws Exception {
        RequestTransactionDTO transactionDto = new RequestTransactionDTO(); 
        transactionDto.setAccountID(1);
        transactionDto.setImpValue(Double.valueOf("10.45"));
        
        ObjectMapper mapper = new ObjectMapper();
        String requestTransaction = mapper.writeValueAsString(transactionDto); 
        
        RequestBuilder request = MockMvcRequestBuilders
            .put("/newTransaction")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestTransaction);

        // this.mockMvc.perform(request).andReturn();
        this.mockMvc.perform(request).andExpect(status().isOk());
    }
}
