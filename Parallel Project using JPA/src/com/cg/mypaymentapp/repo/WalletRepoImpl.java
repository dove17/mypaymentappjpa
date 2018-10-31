package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.mypaymentapp.DBUtil.WalletJPAUtil;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;

public class WalletRepoImpl implements WalletRepo 
{
	private EntityManager entityManager;
	private Map<String, Customer> data; 
   
    private String details = null;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Customer customer;
    private Wallet wallet;
    private Date dateOfTransaction;
    
	public WalletRepoImpl()
	{
		data = new HashMap<String, Customer>();
	    customer = new Customer();
	    entityManager = WalletJPAUtil.getEntityManager();
	}

	public WalletRepoImpl(Map<String, Customer> data) 
	{
		
		this.data = data;
	}
	
	

	
	
	@Override
	public boolean save(Customer customer) 
	{
		
		  entityManager.persist(customer);
		
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) 
	{
		customer = new Customer();
		customer = entityManager.find(Customer.class, mobileNo);

		return customer;
	}

	
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		

		        customer = new Customer();
		        customer = entityManager.find(Customer.class, mobileNo);
		        BigDecimal bal = customer.getWallet().getBalance().add(amount);
		        wallet = new Wallet(bal);
		        customer.setWallet(wallet);
		        
		        entityManager.merge(customer);

		        
		        Date dateOfTransaction = new Date();
				details = "Wallet with mobile number: "+customer.getMobileNo()+" credited with "+ amount+" on "+dateFormat.format(dateOfTransaction)+" with Updated balance: "+customer.getWallet().getBalance();
				Transactions transactions = new Transactions();
				transactions.setMobileNo(mobileNo);
				transactions.setTransactionDetails(details);

		        entityManager.persist(transactions);

		        

		return customer;
			
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		customer = new Customer();
		customer = entityManager.find(Customer.class, mobileNo);
        BigDecimal bal = customer.getWallet().getBalance().subtract(amount);
        wallet = new Wallet(bal);
        customer.setWallet(wallet);
        entityManager.merge(customer);
        
        Date dateOfTransaction = new Date();
		details = "Wallet with mobile number: "+customer.getMobileNo()+" debited with "+ amount+" on "+dateFormat.format(dateOfTransaction)+" with Updated balance: "+customer.getWallet().getBalance();
		Transactions transactions = new Transactions();
		transactions.setMobileNo(mobileNo);
		transactions.setTransactionDetails(details);

        entityManager.persist(transactions);
//		
		return customer;
	}


	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		customer = new Customer();
		
		
		customer = new Customer();
		customer = entityManager.find(Customer.class, sourceMobileNo);
        BigDecimal bal = customer.getWallet().getBalance().subtract(amount);
        wallet = new Wallet(bal);
        customer.setWallet(wallet);
        entityManager.merge(customer);
        
        dateOfTransaction = new Date();
		details = "Wallet with mobile number: "+customer.getMobileNo()+" has Fund transfer of "+ amount+" to mobile number "+targetMobileNo+" on "+dateFormat.format(dateOfTransaction)+" with Updated balance: "+bal;
		Transactions transactions = new Transactions();
		transactions.setMobileNo(sourceMobileNo);
		transactions.setTransactionDetails(details);

        entityManager.persist(transactions);
        
        customer = new Customer();
        customer = entityManager.find(Customer.class, targetMobileNo);
        BigDecimal bal1 = customer.getWallet().getBalance().add(amount);
        wallet = new Wallet(bal1);
        customer.setWallet(wallet);
        entityManager.merge(customer);
        
		dateOfTransaction = new Date();
		details = "Wallet with mobile number: "+customer.getMobileNo()+" has Fund transfer of "+ amount+" from mobile number "+sourceMobileNo+" on "+dateFormat.format(dateOfTransaction)+" with Updated balance: "+bal1;
		transactions = new Transactions();
		transactions.setMobileNo(targetMobileNo);
		transactions.setTransactionDetails(details);

        entityManager.persist(transactions);
		

		return customer;
		
	}

	@Override
	public List<String> showTransactionDetails(String mobileNo) 
	{

		List<Transactions> TransactionsList1 = new ArrayList<Transactions>();
		List<String> TransactionsList = new ArrayList<String>();
		String str = "select trans from Transactions trans where mobile_no=:mob";
		TypedQuery<Transactions> query = entityManager.createQuery(str, Transactions.class);
		query.setParameter("mob", mobileNo);
		
		TransactionsList1 = query.getResultList();
		Iterator<Transactions> it = TransactionsList1.iterator();
		it.next();
		while(it.hasNext())
			TransactionsList.add(it.next().getTransactionDetails());
		
		return TransactionsList;
	}

	@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();
		
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();
		
	}
	

	
	

	

}
