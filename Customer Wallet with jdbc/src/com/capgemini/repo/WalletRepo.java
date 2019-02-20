package com.capgemini.repo;

import java.sql.SQLException;

import com.capgemini.beans.Customer;

public interface WalletRepo {
	
	public int save(Customer customer) throws SQLException;
	
	public Customer findOne(String mobileNo) throws SQLException;

}
