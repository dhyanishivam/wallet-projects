package com.capgemini.beans;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
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
	}

	public Wallet(BigDecimal balance) {
		super();
		this.balance = balance;
	}
	
	

}
