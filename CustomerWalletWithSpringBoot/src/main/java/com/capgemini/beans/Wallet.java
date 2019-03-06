package com.capgemini.beans;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

@Embeddable
public class Wallet {
	
	private BigDecimal amount;

	public Wallet(BigDecimal amount) {
		super();
		this.amount = amount;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
