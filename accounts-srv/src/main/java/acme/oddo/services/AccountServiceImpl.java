package acme.oddo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import acme.oddo.controllers.user.dto.TransactionDto;
import acme.oddo.data.entities.AccountEntity;
import acme.oddo.data.entities.CustomerEntity;
import acme.oddo.data.repositories.AccountRepository;
import acme.oddo.data.repositories.CustomerRepository;
import acme.oddo.services.dto.RequestTransactionDTO;
import acme.oddo.services.dto.ResponseAccountDto;
import acme.oddo.utils.AccountConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository; 

    @Autowired
    CustomerRepository customerRepository;

    @Value("${server.transaction-srv}")
    private String serverTransaction;

    
    @Value("${server.transaction-srv.new.transaction}")
    private String endPointNewTransaction; 


    @Override
    public ResponseAccountDto createAccount(Integer customerID, Double initialCredit) {
        ResponseAccountDto accountDto = new ResponseAccountDto();
        try {
            CustomerEntity customer = customerRepository.findByCustomerID(customerID); 
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setCustomer(customer);
            accountEntity = accountRepository.save(accountEntity); 
            accountDto.setAccountID(accountEntity.getAccountID());
            accountDto.setCustomerID(accountEntity.getCustomer().getCustomerID());
            if (initialCredit > 0) {
                TransactionDto transaction = createTransaction(initialCredit, accountEntity.getCustomer().getCustomerID(), accountEntity.getAccountID());
                if (transaction != null) {
                    accountDto.setInitialCredit(transaction.getImpValue());
                    accountDto.setTransactionDate(transaction.getTransactionDate());
                } else {
                   return null; 
                }
            }        
            return accountDto; 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return accountDto;
        }
    }
    
    private TransactionDto createTransaction(Double initialCredit, Integer customerID, Integer accountID) {
        try {
            RestTemplate restTemplate = new RestTemplate(); 
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            String urlT = getNewTransactionUrl();
            HttpEntity<RequestTransactionDTO> request = getRequestEntity(initialCredit, customerID, accountID);
            TransactionDto response = restTemplate.postForObject(urlT, request, TransactionDto.class);
            if (response != null) {
                return response;
            }
            return null; 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return null; 
        }
    }

    private HttpEntity<RequestTransactionDTO> getRequestEntity(Double initialCredit, Integer customerID, Integer accountID) {
        try { 
            RequestTransactionDTO request = new RequestTransactionDTO(); 
            request.setAccountID(accountID);
            request.setCustomerID(customerID);
            request.setImpValue(initialCredit);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new HttpEntity<>(request, headers);
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return null; 
        }
    }

    private String getNewTransactionUrl() {
        try {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append(serverTransaction);
            strBuild.append(endPointNewTransaction);
            return strBuild.toString(); 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());
            return null; 
        }
    }

    @Override
    public boolean isAccountPresent(Integer customerID, Integer accountID) {
        try {
            if (customerRepository.findByCustomerID(customerID) == null) {
                return false; 
            } 
            if (accountRepository.findByAccountIDAndCustomer_CustomerID(accountID, customerID) == null) {
                return false;
            }
            return true; 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());
            return false;
        }
        
    }

    @Override
    public List<Integer> listAccountByCustomer(Integer customerID) {
        List<Integer> listAccount = new ArrayList<>();
        try {
            if (customerRepository.findByCustomerID(customerID) == null) {
                return listAccount; 
            } 
            List<AccountEntity> listAccEntity = accountRepository.findAllByCustomer_CustomerID(customerID);
            for (AccountEntity accountEntity : listAccEntity) {
                Integer accountToAdd = accountEntity.getAccountID();
                listAccount.add(accountToAdd);
            }
            return listAccount;     
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());
            return listAccount;
        }
        
    }

}
