package acme.oddo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import acme.oddo.controllers.user.dto.AccountInfoDto;
import acme.oddo.controllers.user.dto.AccountsUserInfoDto;
import acme.oddo.controllers.user.dto.CustomerDto;
import acme.oddo.controllers.user.dto.ResponseReportDto;
import acme.oddo.controllers.user.dto.TransactionDto;
import acme.oddo.controllers.user.dto.TransactionSimpleDto;
import acme.oddo.controllers.user.dto.UserInfoDto;
import acme.oddo.utils.AccountConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionReportServiceImpl implements TransactionReportService{

    @Value("${server.transaction-srv}")
    private String serverTransaction;

    
    @Value("${server.transaction-srv.report.account}")
    private String endPointReportTransaction; 

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService; 

    @Override
    public UserInfoDto createReport(Integer customerID, Integer accountID) throws NullPointerException {
        UserInfoDto uDto = new UserInfoDto();
        try {
            if (!customerService.isCustomerPresent(customerID)) {
                return null; 
            }
            uDto = getListTransactionAndBalance(customerID, accountID);
            uDto.setAccountID(accountID);
            CustomerDto customer = customerService.getCustomer(customerID); 
            uDto.setCustomerName(customer.getName());
            uDto.setCustomerSurname(customer.getSurname());
            return uDto;
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return uDto; 
        }   
    }

    private UserInfoDto getListTransactionAndBalance(Integer customerID, Integer accountID) {
        UserInfoDto userDto = new UserInfoDto(); 
        try {
            RestTemplate restTemplate = new RestTemplate(); 
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            String urlT = getNewTransactionUrl();
            Map<String, Integer> vars = new HashMap<>();
            vars.put("customerID", customerID);
            vars.put("accountID", accountID);
            ResponseReportDto response = restTemplate.getForObject(urlT, ResponseReportDto.class, vars);
            if (response != null) {
                userDto.setBalance(response.getBalance());
                userDto.setListTransaction(convertResponseToDto(response));;
                return userDto;
            }
            return  userDto;
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return userDto; 
        }
    }

    private List<TransactionSimpleDto> convertResponseToDto(ResponseReportDto response) {
        List<TransactionSimpleDto> simpleDtos = new ArrayList<>();
        try {
            List<TransactionDto> listTransactions = response.getLDtos(); 
            for (TransactionDto transactionDto : listTransactions) {
                TransactionSimpleDto simpleDto = new TransactionSimpleDto();
                simpleDto.setImpValue(transactionDto.getImpValue());
                simpleDto.setTransactionDate(transactionDto.getTransactionDate());
                simpleDtos.add(simpleDto);
            }   
            return simpleDtos; 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage()); 
            return simpleDtos;
        }
    }

    private String getNewTransactionUrl() {
        try {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append(serverTransaction);
            strBuild.append(endPointReportTransaction);
            return strBuild.toString(); 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());
            return null; 
        }
    }

    @Override
    public AccountsUserInfoDto getTransactionAndAccountsForCustomer(Integer customerID) {
        AccountsUserInfoDto allAccountCustomer = new AccountsUserInfoDto(); 
        try {
            if (customerService.isCustomerPresent(customerID)) {
                CustomerDto customer = customerService.getCustomer(customerID);
                allAccountCustomer.setCustomerName(customer.getName());
                allAccountCustomer.setCustomerSurname(customer.getSurname());    
            } else {
                return allAccountCustomer; 
            }
            allAccountCustomer.setListAccountInfo(new ArrayList<>());
            List<Integer> listAccount = accountService.listAccountByCustomer(customerID); 
            for (Integer accountID : listAccount) {
                UserInfoDto uDto = getListTransactionAndBalance(customerID, accountID); 
                AccountInfoDto accDto = new AccountInfoDto(); 
                accDto.setAccountID(accountID);
                accDto.setBalalnceAccount(uDto.getBalance());
                List<TransactionSimpleDto> listTran = new ArrayList<>();
                listTran = uDto.getListTransaction(); 
                accDto.setListTransaction(listTran);
                allAccountCustomer.getListAccountInfo().add(accDto); 
            }
            return allAccountCustomer;            
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());
            e.printStackTrace();
            return allAccountCustomer;
        }

    }

    
}
