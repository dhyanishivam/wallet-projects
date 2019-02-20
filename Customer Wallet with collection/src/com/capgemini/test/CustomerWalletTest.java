package com.capgemini.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;
import com.capgemini.service.WalletServiceImpl;

public class CustomerWalletTest {

WalletServiceImpl walletService = new WalletServiceImpl();
	
	@Test
	public void whenAllInputAreCorrectWhileCreatingWallet() throws DuplicateMobileNumberException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
	}
	
	@Test(expected=com.capgemini.exceptions.DuplicateMobileNumberException.class)
	public void whenDuplicateMobileNumberFoundWhenCreatingWallet() throws DuplicateMobileNumberException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(6000);
		walletService.createAccount("Dhyani", "8859552994", amount2);
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundWhenShowingBalance() throws MobileNumberNotFoundException
	{
		walletService.showBalance("8859552994");
	}
	
	@Test
	public void whenDetailsAreShowedWithoutAnyException() throws DuplicateMobileNumberException, MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		walletService.showBalance("8859552994");
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundWhenDoingFundTransfer() throws MobileNumberNotFoundException, InsufficientBalanceException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.fundTransfer("8859552994","7894561230",amount1);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenBalanceIsNotSufficientForFundTransferring() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientBalanceException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(6000);
		walletService.createAccount("Dhyani", "7894561230", amount2);
		BigDecimal amount3 = new BigDecimal(6000);
		walletService.fundTransfer("8859552994", "7894561230", amount3);
	}
	
	@Test
	public void whenAllInputsAreAppropriateForFundTransfer() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientBalanceException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(6000);
		walletService.createAccount("Dhyani", "7894561230", amount2);
		BigDecimal amount3 = new BigDecimal(1000);
		walletService.fundTransfer("8859552994", "7894561230", amount3);
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundWhenDeposit() throws MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.depositAmount("8859552994", amount1);
	}
	
	@Test
	public void whenAllInputsAreAppropriateForDeposit() throws DuplicateMobileNumberException, MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(6000);
		walletService.depositAmount("8859552994", amount2);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenBalanceIsNotSufficientForWithDrawl() throws DuplicateMobileNumberException, InsufficientBalanceException, MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(6000);
		walletService.withdrawAmount("8859552994", amount2);
	}
	
	@Test(expected=com.capgemini.exceptions.MobileNumberNotFoundException.class)
	public void whenMobileNumberNotFoundWhenWithdrawing() throws InsufficientBalanceException, MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.withdrawAmount("8859552994", amount1);
	}
	
	@Test
	public void whenAllInputsAreAppropriateForWithdrawl() throws DuplicateMobileNumberException, InsufficientBalanceException, MobileNumberNotFoundException
	{
		BigDecimal amount1 = new BigDecimal(5000);
		walletService.createAccount("Shivam", "8859552994", amount1);
		BigDecimal amount2 = new BigDecimal(1000);
		walletService.withdrawAmount("8859552994", amount2);
	}
}
