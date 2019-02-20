package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;

public interface WalletService {
	
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException;
	
	public Customer showBalance(String mobileNo) throws MobileNumberNotFoundException;
	
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNumberNotFoundException, InsufficientBalanceException;
	
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNumberNotFoundException;
	
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException, MobileNumberNotFoundException;

}
