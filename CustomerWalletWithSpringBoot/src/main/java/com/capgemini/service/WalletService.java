package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;

public interface WalletService {
	
	public Customer save(Customer customer);
	public Customer findOne(String mobileNo);
	public Customer Deposit(String mobileNo, BigDecimal amount);
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);
	public Customer withdrawl(String mobileNo, BigDecimal amount);
}
