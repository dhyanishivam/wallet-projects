package com.capgemini.repo;

import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;

public class WalletRepoImpl implements WalletRepo{
	EntityManager manager;
	
	

	public WalletRepoImpl() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("wallet");
		manager = emf.createEntityManager();
	}
	@Override
	public void save(Customer customer) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}
	public Customer fundtransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws SQLException
	{
		manager.getTransaction().begin();
		Customer sourceCustomer=manager.find(Customer.class, sourceMobileNo);
		Customer targetCustomer=manager.find(Customer.class, targetMobileNo);
		
		Wallet sWallet=sourceCustomer.getWallet();
		Wallet tWallet=targetCustomer.getWallet();
		
		sWallet.setBalance(sWallet.getBalance().subtract(amount));
		sourceCustomer.setWallet(sWallet);
		tWallet.setBalance(tWallet.getBalance().add(amount));
		targetCustomer.setWallet(tWallet);
		manager.getTransaction().commit();
		return targetCustomer;
	}
	public Customer deposit(String mobileNo, BigDecimal amount) throws SQLException
	{
		manager.getTransaction().begin();
		Customer customer=manager.find(Customer.class, mobileNo);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(amount));
		customer.setWallet(wallet);
		manager.getTransaction().commit();
		
		return customer;
	}
	
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws SQLException
	{
		manager.getTransaction().begin();
		Customer customer=manager.find(Customer.class, mobileNo);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(wallet.getBalance().subtract(amount));
		customer.setWallet(wallet);
		manager.getTransaction().commit();
		
		return customer;
	}
	@Override
	public Customer findOne(String mobileNo) throws SQLException {
		manager.getTransaction().begin();
		Customer c=manager.find(Customer.class, mobileNo);
		manager.getTransaction().commit();
		return c;
	}
	 

}
