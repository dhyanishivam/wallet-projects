package com.capgemini.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.repo.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	WalletRepo walletRepo;

	@Override
	public Customer save(Customer customer) {
			if(findOne(customer.getMobileNo())==null)
			{
				return walletRepo.save(customer);
			}
			return null;
		}

	@Override
	public Customer findOne(String mobileNo) 
	{
		if(walletRepo.findById(mobileNo).isPresent())
		{
			return walletRepo.findById(mobileNo).get();
		}
		return null;
	}

	@Override
	public Customer Deposit(String mobileNo, BigDecimal amount) {
		if(findOne(mobileNo)!=null)
		{
			Customer customer = findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setAmount(wallet.getAmount().add(amount));
			customer.setWallet(wallet);
			walletRepo.save(customer);
			return customer;
		}
		
		return null;
	}

	@Override
	public Customer withdrawl(String mobileNo, BigDecimal amount) {
		if(findOne(mobileNo)!=null)
		{
			Customer customer = findOne(mobileNo);
			if(customer.getWallet().getAmount().intValue()>amount.intValue())
			{
				Wallet wallet = customer.getWallet();
				wallet.setAmount(wallet.getAmount().subtract(amount));
				customer.setWallet(wallet);
				walletRepo.save(customer);
				return customer;
			}
		}
		return null;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		if(findOne(sourceMobileNo)!=null && findOne(targetMobileNo)!=null)
		{
			Customer sourceCustomer = findOne(sourceMobileNo);
			if(sourceCustomer.getWallet().getAmount().intValue()>amount.intValue())
			{
				Customer targetCustomer = findOne(targetMobileNo);
				Wallet wallet = sourceCustomer.getWallet();
				wallet.setAmount(wallet.getAmount().subtract(amount));
				sourceCustomer.setWallet(wallet);
				
				wallet = targetCustomer.getWallet();
				wallet.setAmount(wallet.getAmount().add(amount));
				targetCustomer.setWallet(wallet);
				walletRepo.save(sourceCustomer);
				walletRepo.save(targetCustomer);
				return sourceCustomer;
			}
		}
		return null;
	}
}
