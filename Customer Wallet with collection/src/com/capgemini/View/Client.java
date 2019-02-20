package com.capgemini.View;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.DuplicateMobileNumberException;
import com.capgemini.service.WalletServiceImpl;

public class Client {

	public static void main(String[] args) {
		
		WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
		BigDecimal bigDecimal1 = new BigDecimal(1000);
		BigDecimal bigDecimal2 = new BigDecimal(5000);
		try {
			walletServiceImpl.createAccount("Shivam", "8859552994", bigDecimal1);
		} catch (DuplicateMobileNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			walletServiceImpl.createAccount("vikash", "8859552993", bigDecimal2);
		} catch (DuplicateMobileNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
