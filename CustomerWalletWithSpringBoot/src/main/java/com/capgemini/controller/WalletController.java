package com.capgemini.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.service.WalletService;

@RestController
public class WalletController {
	
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value="walletAdd", method=RequestMethod.POST)
	public Customer create(@RequestBody Customer customer)
	{
		return walletService.save(customer);
	}
	
	@RequestMapping(value="walletFind/{mobileNo}", method=RequestMethod.GET)
	public Customer search(@PathVariable String mobileNo)
	{
		return walletService.findOne(mobileNo);
	}
	
	@RequestMapping(value="walletDeposit/{mobileNo}/{balance}", method=RequestMethod.GET)
	public Customer deposit(@PathVariable String mobileNo, @PathVariable int balance)
	{
		BigDecimal amount = new BigDecimal(balance);
		return walletService.Deposit(mobileNo, amount);
	}
	
	@RequestMapping(value="walletF/{sourceMobileNo}/{targetMobileNo}/{balance}", method=RequestMethod.GET)
	public Customer fundTransfer(@PathVariable String sourceMobileNo, @PathVariable String targetMobileNo, @PathVariable int balance)
	{
		BigDecimal amount = new BigDecimal(balance);
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	}
	
	@RequestMapping(value="walletWithdrawl/{mobileNo}/{balance}", method=RequestMethod.GET)
	public Customer withdrawl(@PathVariable String mobileNo, @PathVariable int balance)
	{
		BigDecimal amount = new BigDecimal(balance);
		return walletService.withdrawl(mobileNo, amount);
	}
	
	

}
