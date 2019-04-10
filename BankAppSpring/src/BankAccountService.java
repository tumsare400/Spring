package com.capgemini.bank.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.capgemini.bank.exception.BankAccountNotFoundException;
import com.capgemini.bank.exception.LowBalanceException;
import com.capgemini.bank.model.BankAccount;

public interface BankAccountService {

	public double checkBalance(long accountId) throws BankAccountNotFoundException;

	public double withdraw(long accountId, double amount) throws LowBalanceException, BankAccountNotFoundException, SQLException;

	public double deposit(long accountId, double amount) throws BankAccountNotFoundException, SQLException;

	public boolean deleteBankAccount(long accountId)throws BankAccountNotFoundException;

	public double fundTransfer(long fromAccount, long toAccount, double amount)
			throws LowBalanceException, BankAccountNotFoundException, SQLException, Exception;

	public boolean addNewBankAccount(BankAccount account);

	public List<BankAccount> findAllBankAccount();

	public List<BankAccount> searchBankAccount(long accountId)throws BankAccountNotFoundException;

	public boolean updateAccountDetails(long accountId, String accountHolderName, String accountType)throws BankAccountNotFoundException;

}
