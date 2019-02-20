package com.capgemini.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;
import com.capgemini.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{
	WalletRepoImpl walletRepoImpl = new WalletRepoImpl();
	

	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException, SQLException
	{
		if(walletRepoImpl.findOne(mobileNo)==null)
		{
			Wallet wallet = new Wallet(amount);
			Customer customer = new Customer(name,mobileNo,wallet);
			walletRepoImpl.save(customer);
			return customer;
		}
		throw new DuplicateMobileNumberException();
	}

	@Override
	public Customer showBalance(String mobileNo) throws MobileNumberNotFoundException, SQLException
	{
		if(walletRepoImpl.findOne(mobileNo)!=null)
		{
			Customer customer = walletRepoImpl.findOne(mobileNo);
			return customer;
		}
		throw new MobileNumberNotFoundException();
	}
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNumberNotFoundException, InsufficientBalanceException, SQLException
	{
		if(walletRepoImpl.findOne(sourceMobileNo)!=null && walletRepoImpl.findOne(targetMobileNo)!=null)
		{
			Customer customerSource = walletRepoImpl.findOne(sourceMobileNo);
			if(customerSource.getWallet().getBalance().compareTo(amount)>0)
			{
				
				return walletRepoImpl.fundtransfer(sourceMobileNo, targetMobileNo, amount);
			}
			throw new InsufficientBalanceException();
		}
		throw new MobileNumberNotFoundException();
		
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount)throws MobileNumberNotFoundException, SQLException
	{
		if(walletRepoImpl.findOne(mobileNo)!=null)
		{
			return walletRepoImpl.deposit(mobileNo, amount);
		}
		
		throw new MobileNumberNotFoundException();
		
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException, MobileNumberNotFoundException, SQLException 
	{
		if(walletRepoImpl.findOne(mobileNo)!=null)
		{
			Customer customer = walletRepoImpl.findOne(mobileNo);
			if(customer.getWallet().getBalance().compareTo(amount)>0)
			{
				Wallet wallet = customer.getWallet();
				wallet.setBalance(wallet.getBalance().subtract(amount));
				customer.setWallet(wallet);
				return customer;
			}
			throw new InsufficientBalanceException();
		}
		throw new MobileNumberNotFoundException();
	}

}
