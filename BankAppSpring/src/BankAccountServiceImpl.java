package com.capgemini.bank.service.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.capgemini.bank.dao.BankAccountDao;
import com.capgemini.bank.dao.impl.BankAccountDaoImpl;
import com.capgemini.bank.exception.BankAccountNotFoundException;
import com.capgemini.bank.exception.LowBalanceException;
import com.capgemini.bank.model.BankAccount;
import com.capgemini.bank.service.BankAccountService;
import com.capgemini.bank.util.DbUtil;

public class BankAccountServiceImpl implements BankAccountService {

	static Logger logger = Logger.getLogger(BankAccountServiceImpl.class);

	private BankAccountDao bankAccountDao;

	public BankAccountServiceImpl(BankAccountDao bankAccountDao) {

		this.bankAccountDao = bankAccountDao;

	}

	@Override
	public double checkBalance(long accountId) throws BankAccountNotFoundException {
		double balance = bankAccountDao.getBalance(accountId);
		if (balance >= 0) {
			return bankAccountDao.getBalance(accountId);
		} else
			throw new BankAccountNotFoundException("You have entered incorrect bank account details..");

	}

	@Override
	public double withdraw(long accountId, double amount)
			throws LowBalanceException, SQLException, BankAccountNotFoundException {

		double balance = bankAccountDao.getBalance(accountId);

		if (balance >= 0) {
			if (balance - amount >= 0) {
				balance = balance - amount;
				bankAccountDao.updateBalance(accountId, balance);
				DbUtil.commmit();

				return balance;
			}

			else{
				throw new LowBalanceException("You do not have sufficient fund...");
			}
		}

		else{
			throw new BankAccountNotFoundException("You have entered incorrect bank account details..");
		}
	}

	public double withdrawForFundTransfer(long accountId, double amount)
			throws LowBalanceException, SQLException, BankAccountNotFoundException {

		double balance = bankAccountDao.getBalance(accountId);

		if (balance >= 0) {
			if (balance - amount >= 0) {
				balance = balance - amount;

				return balance;
			}

			else
				throw new LowBalanceException("You do not have sufficient fund...");
		}

		else
			throw new BankAccountNotFoundException("You have entered incorrect bank account details..");
	}

	@Override
	public double deposit(long accountId, double amount) throws SQLException, BankAccountNotFoundException {
		double balance = bankAccountDao.getBalance(accountId);
		if (balance >= 0) {
			balance = balance + amount;
			bankAccountDao.updateBalance(accountId, balance);
			DbUtil.commmit();
			return balance;
		} else
			throw new BankAccountNotFoundException("You have entered incorrect account details..");
	}

	@Override
	public boolean deleteBankAccount(long accountId) throws BankAccountNotFoundException {
		boolean result = bankAccountDao.deleteBankAccount(accountId);
		if (result) {
			DbUtil.commmit();
			return result;
		}
		throw new BankAccountNotFoundException("You have entered incorrect account details..");
	}

	@Override
	public double fundTransfer(long fromAccount, long toAccount, double amount) throws Exception {
		try {
			double newBalance = withdrawForFundTransfer(fromAccount, amount);
			deposit(toAccount, amount);
			DbUtil.commmit();
			return newBalance;
		} catch (LowBalanceException | BankAccountNotFoundException | SQLException e) {
			logger.error("Exception", e);
			DbUtil.rollback();
			throw e;
		}

	}

	@Override
	public boolean addNewBankAccount(BankAccount account) {
		boolean result = bankAccountDao.addNewBankAccount(account);
		if (result)
			DbUtil.commmit();
		return result;

	}

	@Override
	public List<BankAccount> findAllBankAccount() {
		return bankAccountDao.findAllBankAccount();
	}

	@Override
	public List<BankAccount> searchBankAccount(long accountId) throws BankAccountNotFoundException {
		List<BankAccount> result = bankAccountDao.searchBankAccount(accountId);
		try {
			if (!result.isEmpty())
				return result;
			else
				throw new BankAccountNotFoundException("You have entered incorrect account details..");
		}

		catch (BankAccountNotFoundException e) {
			logger.error("Exception", e);
			throw e;

		}
	}

	@Override
	public boolean updateAccountDetails(long accountId, String accountHolderName, String accountType) throws BankAccountNotFoundException {
		List<BankAccount> result = bankAccountDao.searchBankAccount(accountId);
		boolean result1 = false;
		try {
		if (result != null) {
			result1 = bankAccountDao.updateDetails(accountId, accountHolderName, accountType);
		DbUtil.commmit();
		return result1;}
		else
			throw new BankAccountNotFoundException("You have entered incorrect account details..");
		
		}
		
		catch (BankAccountNotFoundException e) {
			logger.error("Exception", e);
			throw e;

		}
	}
	
	
	

}
