package com.capgemini.repo;

import com.capgemini.beans.Customer;

public interface WalletRepo {
	
	public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);

}
