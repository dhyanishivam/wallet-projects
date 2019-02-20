package com.capgemini.repo;

import java.util.LinkedList;
import com.capgemini.beans.Customer;

public class WalletRepoImpl implements WalletRepo{
	LinkedList<Customer> customers = new LinkedList<>();

	@Override
	public boolean save(Customer customer) {
		customers.add(customer);
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) {
		for(Customer i:customers)
		{
			if(i.getMobileNo().equals(mobileNo))
			{
				return i;
			}
		}
		
		return null;
	}
	
	

}
