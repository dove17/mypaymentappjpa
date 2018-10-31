package com.cg.mypaymentapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Wallet")
public class Wallet implements Serializable  
{

	@Id
	@Column(name="wallet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int walletId;  

	@Column(name="wallet_balance")
	private BigDecimal balance;


	public Wallet() {

	}

	public Wallet(BigDecimal amount) {
		this.balance=amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return ", balance="+balance;
	}
}
