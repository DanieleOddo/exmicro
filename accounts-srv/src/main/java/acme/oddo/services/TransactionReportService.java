package acme.oddo.services;

import acme.oddo.controllers.user.dto.AccountsUserInfoDto;
import acme.oddo.controllers.user.dto.UserInfoDto;

public interface TransactionReportService {
    
    public UserInfoDto createReport(Integer customerID, Integer accountID); 

    public AccountsUserInfoDto getTransactionAndAccountsForCustomer(Integer customerID);
}
