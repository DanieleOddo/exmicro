package acme.oddo.controllers.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.services.TransactionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;


@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    TransactionService transactionService;

    @Test
    void itShouldBeReportBadRequest() throws Exception {

        this.mvc.perform(get("/reportAccount")
            .param("account", "10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getReport() throws Exception {

        RequestTransactionDTO re1 = new RequestTransactionDTO();
        re1.setAccountID(1);
        re1.setImpValue(Double.parseDouble("10.00"));
        transactionService.createTransaction(re1);

        RequestTransactionDTO re2 = new RequestTransactionDTO();
        re2.setAccountID(1);
        re2.setImpValue(Double.parseDouble("10.00"));
        transactionService.createTransaction(re2);
        
        this.mvc.perform(get("/reportAccount")
            .param("account", "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
