package com.capgemini.View;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberNotFoundException;
import com.capgemini.service.WalletServiceImpl;

public class Client {
	static Scanner sc = new Scanner(System.in);
	static WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
	public static void main(String[] args) throws DuplicateMobileNumberException, SQLException {
		
		
		
		while(true)
		{
			System.out.println("1) Create Account");
			System.out.println("2) Show Balance");
			System.out.println("3) Fund transfer");
			System.out.println("4) Deposit Amount");
			System.out.println("5) Withdraw Amount");
			System.out.println("6) Exit");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:createAccount();
				break;
			case 2:showBalance();
				break;
			case 3:fundTransfer();
				break;
			case 4:deposit();
				break;
			case 5:withdraw();
				break;
			case 6:
				System.exit(0);
			default:System.out.println("Invalid choice");
			}
		}
		

	}

	private static void withdraw() {
		System.out.println("Enter mobile number:");
		String mobileNo=sc.next();
		System.out.println("Enter amount:");
		int amt=sc.nextInt();
		BigDecimal amount = new BigDecimal(amt);
		Customer c=null;
		try
		{
			c=walletServiceImpl.withdrawAmount(mobileNo, amount);
			System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance().intValue()+" is updated");
		}
		catch (InsufficientBalanceException e)
		{
			System.out.println("Insufficient Balance Exception");
		}
		catch (MobileNumberNotFoundException e)
		{
			System.out.println("Mobile Number Not Found");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	private static void deposit() {
		System.out.println("Enter mobile number:");
		String mobileNo=sc.next();
		System.out.println("Enter amount:");
		int amt=sc.nextInt();
		BigDecimal amount = new BigDecimal(amt);
		Customer c=null;
		try
		{
			c=walletServiceImpl.depositAmount(mobileNo, amount);
			System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance().intValue()+" is updated");
		}
		catch (MobileNumberNotFoundException e)
		{
			System.out.println("Mobile Number Not Found");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void fundTransfer() {
		System.out.println("Enter source mobile number:");
		String sourceMobileNo=sc.next();
		System.out.println("Enter target mobile number:");
		String targetMobileNo=sc.next();
		System.out.println("Enter amount:");
		int amt=sc.nextInt();
		BigDecimal amount = new BigDecimal(amt);
		Customer c=null;
		try
		{
			c=walletServiceImpl.fundTransfer(sourceMobileNo, targetMobileNo, amount);
			System.out.println("New Balance of "+c.getMobileNo()+" is "+c.getWallet().getBalance());
		}
		catch (MobileNumberNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InsufficientBalanceException e)
		{
			System.out.println("Insufficient Balance Exception");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	private static void showBalance(){
		System.out.println("Enter mobile number:");
		String mobileNo=sc.next();
		Customer c=null;
		try 
		{
			c = walletServiceImpl.showBalance(mobileNo);
			System.out.println("Mobile No.:"+c.getMobileNo()+" Balance:"+c.getWallet().getBalance());
		}
		catch (MobileNumberNotFoundException e)
		{
			System.out.println("Mobile Number Not Found");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

	private static void createAccount() {
		System.out.println("Enter name:");
		String name = sc.next();
		System.out.println("Enter mobile number:");
		String mobileNo=sc.next();
		System.out.println("Enter amount:");
		int amt=sc.nextInt();
		BigDecimal amount = new BigDecimal(amt);
		Customer c=null;
		try
		{
			c = walletServiceImpl.createAccount(name, mobileNo, amount);
			System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance().intValue()+" is inserted");
		}
		catch (DuplicateMobileNumberException e)
		{
			System.out.println("Duplicate Mobile Number");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}


