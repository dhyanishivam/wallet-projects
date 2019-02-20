package com.capgemini.beans;

import java.math.BigDecimal;

public class Wallet {
	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wallet(BigDecimal balance) {
		super();
		this.balance = balance;
	}
	
	

}
