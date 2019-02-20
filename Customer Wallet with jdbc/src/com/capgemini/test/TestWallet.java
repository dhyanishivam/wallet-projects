package com.capgemini.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;
import com.capgemini.repo.WalletRepoImpl;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class TestWallet {

	@Test(expected=com.capgemini.exceptions.DuplicateMobileNumberException.class)
	public void whenDuplicateMobileNumberPassedInCreatingAccount() throws DuplicateMobileNumberException, SQLException
	{
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		BigDecimal amount = new BigDecimal(2000);
		walletServiceImpl.createAccount("Shivam", "8859552994", amount);
	}
	
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundForShowingBalance() throws MobileNumberNotFoundException, SQLException
	{
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		walletServiceImpl.showBalance("5791324863");
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundForFundTransfer() throws MobileNumberNotFoundException, InsufficientBalanceException, SQLException
	{
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		BigDecimal amount = new BigDecimal(2000);
		walletServiceImpl.fundTransfer("9999999999", "8888888888", amount);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenInsufficientBalanceForFundTransfer() throws MobileNumberNotFoundException, InsufficientBalanceException, SQLException
	{
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		BigDecimal amount = new BigDecimal(2000);
		walletServiceImpl.fundTransfer("8859552994", "8859552993", amount);
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundForDeposit() throws MobileNumberNotFoundException, SQLException
	{
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		BigDecimal amount = new BigDecimal(2000);
		walletServiceImpl.depositAmount("9999999999", amount);
	}
}



