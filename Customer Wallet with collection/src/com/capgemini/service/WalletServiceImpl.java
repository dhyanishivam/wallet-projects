package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{
	WalletRepoImpl walletRepoImpl = new WalletRepoImpl();
	

	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException
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
	public Customer showBalance(String mobileNo) throws MobileNumberNotFoundException
	{
		if(walletRepoImpl.findOne(mobileNo)!=null)
		{
			Customer customer = walletRepoImpl.findOne(mobileNo);
			return customer;
		}
		throw new MobileNumberNotFoundException();
	}
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNumberNotFoundException, InsufficientBalanceException
	{
		if(walletRepoImpl.findOne(sourceMobileNo)!=null && walletRepoImpl.findOne(targetMobileNo)!=null)
		{
			Customer customerSource = walletRepoImpl.findOne(sourceMobileNo);
			Customer customerTarget = walletRepoImpl.findOne(targetMobileNo);
			if(customerSource.getWallet().getBalance().compareTo(amount)>0)
			{
				Wallet walletSource = customerSource.getWallet();
				walletSource.setBalance(walletSource.getBalance().subtract(amount));
				customerSource.setWallet(walletSource);
				
				Wallet walletTarget = customerTarget.getWallet();
				walletTarget.setBalance(walletTarget.getBalance().add(amount));
				customerTarget.setWallet(walletTarget);
				
				return customerTarget;
				
			}
			throw new InsufficientBalanceException();
		}
		throw new MobileNumberNotFoundException();
		
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount)throws MobileNumberNotFoundException
	{
		if(walletRepoImpl.findOne(mobileNo)!=null)
		{
			Customer customer = walletRepoImpl.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().add(amount));
			customer.setWallet(wallet);
			
			return customer;
		}
		
		throw new MobileNumberNotFoundException();
		
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException, MobileNumberNotFoundException 
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
