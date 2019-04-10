package com.capgemini.bankapp.service.impl;



import java.util.List;


import org.apache.log4j.Logger;


import com.capgemini.bankapp.dao.BankAccountDao;

import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;

import com.capgemini.bankapp.exception.AccountNotFoundException;

import com.capgemini.bankapp.exception.LowBalanceException;

import com.capgemini.bankapp.model.BankAccount;

import com.capgemini.bankapp.service.BankAccountService;

import com.capgemini.bankapp.util.DbUtil;

import com.mysql.jdbc.Connection;

import org.springframework.stereotype.*;

import org.springframework.beans.factory.annotation.*;


@Service("serviceImpl")

public class BankAccountServiceImpl implements BankAccountService {

	
	BankAccountDao bankAccountDao;
	
	static final Logger logger = Logger.getLogger(BankAccountServiceImpl.class);

	

       @Autowired
	
       @Qualifier("daoImpl")
	
       public void setBankAccountDao(BankAccountDao bankAccountDao)
{
		
		this.bankAccountDao = bankAccountDao;

	}
	

	
      public BankAccountServiceImpl() {
		
		bankAccountDao = new BankAccountDaoImpl();
	
       }

	

      @Override
	
      public double checkBalance(long accountId) throws AccountNotFoundException {
	

        	double balance = bankAccountDao.getBalance(accountId);
		
			if (balance < 0)
 throw new AccountNotFoundException("Account does not exist");
		
                  		return balance;

	}

	

    @Override
	
    public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException {

		double balance = bankAccountDao.getBalance(accountId);
		
			if (balance < 0)
			
                        throw new AccountNotFoundException("Bank account does not exist");
		
			else if (balance - amount >= 0) {
			
				balance = balance - amount;
			
				bankAccountDao.updateBalance(accountId, balance);
			
				DbUtil.commit();
		
			} else
			
			throw new LowBalanceException("You have insufficient fund...");
		
					return balance;
	
      }

	
	
   private double withdrawForFundTransfer(long fromAccount, double amount) throws AccountNotFoundException, LowBalanceException {

		double balance = bankAccountDao.getBalance(fromAccount);
		
			if (balance < 0)

				throw new AccountNotFoundException("Bank account does not exist");
		
			else if (balance - amount >= 0) {
			
				balance = balance - amount;
			
				bankAccountDao.updateBalance(fromAccount, balance);
		
			} else
			
				throw new LowBalanceException("You have insufficient fund...");
		
				return balance;

	}

	

   @Override
	
   public double deposit(long accountId, double amount) throws AccountNotFoundException {
		
        	double balance = bankAccountDao.getBalance(accountId);
		
				if (balance < 0)
			
					throw new AccountNotFoundException("Account doesn't exist");
		
					balance = balance + amount;
		
					bankAccountDao.updateBalance(accountId, balance);
		
					DbUtil.commit();
		
					return balance;
	
  }

	

  @Override
	
  public boolean deleteBankAccount(long accountId) throws AccountNotFoundException {
		
 		boolean result = bankAccountDao.deleteBankAccount(accountId);
		
			if (result) {
			
				DbUtil.commit();
			
				return true;
	
				}
		
				throw new AccountNotFoundException("Account doesn't exist");
			
	
	}


	
@Override
	
public double fundTransfer(long fromAccount, long toAccount, double amount)
throws LowBalanceException, AccountNotFoundException {
	
	  double newBalance = 0;
		
       try {
			
 		newBalance = withdrawForFundTransfer(fromAccount, amount);
			
		deposit(toAccount, amount);
			
		DbUtil.commit();
			
		return newBalance;
		
		} 
		catch (LowBalanceException | AccountNotFoundException e) {
			
			logger.error("Exception: ", e);
			
			DbUtil.rollback();
			
			throw e;
		
      }	
  }

	

	

@Override
	
public boolean addNewBankAccount(BankAccount account) {
		
		boolean result = bankAccountDao.addNewBankAccount(account);
		
			if(result)
			
			   DbUtil.commit();
		
			  return result;

}

	

@Override
	
public List<BankAccount> findAllBankAccounts() {
	
	return bankAccountDao.findAllBankAccounts();


}

	

@Override
	
public BankAccount searchAccount(long accountId) throws AccountNotFoundException {
		
		BankAccount account = bankAccountDao.searchAccount(accountId);
		
			if (account == null)
			
			throw new AccountNotFoundException("Account doesn't exist");
		
			return account;
	
	}


	
@Override
	
public boolean updateAccountDetails(BankAccount account) {
		
		boolean result = bankAccountDao.updateAccountDetails(account);
		
		DbUtil.commit();
		
		return result;

	}

	


}
