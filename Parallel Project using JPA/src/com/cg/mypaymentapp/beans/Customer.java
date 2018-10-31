package com.cg.mypaymentapp.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;





@Entity
@Table(name="Customer")
public class Customer implements Serializable
{
	@Column(name="c_name")
	private String name;
	
	@Id
	@Column(name="mobile_no")
	private String mobileNo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="trans_id")
	private Transactions transactions;
	
	
	
	public Customer() 
	{
		
	}
	
	public Customer(String name, String mobileNo, Wallet wallet, Transactions transactions) {
		this.name=name;
		this.mobileNo=mobileNo;
		this.wallet=wallet;
		this.transactions = transactions;
}
	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer name=" + name + ", mobileNo=" + mobileNo
				 + wallet;
	}
	
	
}
