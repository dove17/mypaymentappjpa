package com.cg.mypaymentapp.beans;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Transactions")
public class Transactions implements Serializable 
{
	

	@Id
	@Column(name="trans_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transId; 
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="Transaction_Details")
    private String transactionDetails;


	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	public Transactions(String mobileNo) {
		super();
		this.mobileNo = mobileNo;
	}






	public Transactions(int transId, String mobileNo, String transactionDetails) {
		super();
		this.transId = transId;
		this.mobileNo = mobileNo;
		this.transactionDetails = transactionDetails;
	}






	public String getTransactionDetails() {
		return transactionDetails;
	}






	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}






	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	
	
   
	
     
     
     
}
