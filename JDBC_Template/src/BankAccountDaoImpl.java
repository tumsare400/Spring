package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;  
import com.capgemini.bankapp.client.BankAccount;
import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.exception.AccountNotFoundException;
import com.capgemini.bankapp.util.DbUtil;
import javax.sql.DataSource;

public class BankAccountDaoImpl implements BankAccountDao {

	private JdbcTemplate jdbcTemplate;  
	DataSource dataSource;

	/*public BankAccountDaoImpl(){
		super();
	}
	public BankAccountDaoImpl(DataSource dataSource){
		this.dataSource=dataSource;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
    		this.jdbcTemplate = jdbcTemplate;  
	}*/  

	@Override
	public double getBalance(long accountId) {

		
		String query = "SELECT account_balance FROM bankaccounts WHERE account_id="+accountId;
		Double balance = -1.0;
                balance = jdbcTemplate.queryForObject(query, Double.class);

		//Connection connection = DbUtil.getConnection();
	
		/*try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {
			if (result.next()) {
				balance = result.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return balance;
	}

	@Override
	public void updateBalance(long accountId, double newBalance) {
		String query = "UPDATE bankaccounts SET account_balance= '"+newBalance+"' WHERE account_id= '"+accountId+"'";
                int result = jdbcTemplate.update(query);
		System.out.println(result);

                

		//Connection connection = DbUtil.getConnection();
		/*try (Connection connection = dataSource.getConnection();
		        PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);

			int result = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
            
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		String query = "DELETE FROM bankaccounts WHERE account_id=?";
                Object params[] = {accountId};
		int result = jdbcTemplate.update(query, params);
                 
                  if(result==1)
                       return true;

		//Connection connection = DbUtil.getConnection();
		/*try (Connection connection = dataSource.getConnection()) {
			result = statement.executeUpdate();

			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return false;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account) {

		String query = "INSERT INTO bankaccounts (customer_name,account_type,account_balance) VALUES (?,?,?)";
		Object params[] = {account.getAccountHolderName(),account.getAccountType(),account.getAccountBalance()};
		int result = jdbcTemplate.update(query, params);

		if(result==1)
			return true;

		//Connection connection = DbUtil.getConnection();

		/*try (Connection connection = dataSource.getConnection())
		 {

			Object[] args=new Object[3];
			args[0]=account.getAccountHolderName();
			args[1]=account.getAccountType();
			args[2]=account.getAccountBalance();

			int result=jdbcTemplate.update(query);
			if (result == 1) {
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		return false;
	}

	@Override
	public List<BankAccount> findAllBankAccountsDetails() {
		String query = "SELECT * FROM bankaccounts";
		List<BankAccount> accounts = jdbcTemplate.query(query,(resultSet, rowNum)->{
                                              BankAccount account = new BankAccount(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
                                              return account;
                   });

		//Connection connection = DbUtil.getConnection();
		/*try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {

				account = new BankAccount(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDouble(4));
				accounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return accounts;
	}

	@Override
	public BankAccount searchAccountDetails(long accountId) throws AccountNotFoundException {
		String query = "SELECT * FROM bankaccounts WHERE account_id=" + accountId;
		BankAccount account = jdbcTemplate.queryForObject(query,(resultSet, rowNum)->{
                                      BankAccount accounts = new BankAccount(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
                                       return accounts;
                });

		//Connection connection = DbUtil.getConnection();
		try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				account = new BankAccount(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDouble(4));
			} else {
				throw new AccountNotFoundException("BankAccount doesn't exist....");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public boolean updateBankAccountDetails(long accountId, String accountHolderName, String accountType) {

		String query = "UPDATE bankaccounts SET customer_name=?,account_type=? WHERE account_id=?";
                Object params[] = {accountHolderName, accountType,accountId};
                int result = jdbcTemplate.update(query, params);
                
                 if(result==1)
                      return  true;

		//Connection connection = DbUtil.getConnection();
		/*try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, accountHolderName);
			statement.setString(2, accountType);
			statement.setLong(3, accountId);

			int result = statement.executeUpdate();

			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return false;
	}
	
 	public void setDataSource(DataSource dataSource) {
 		this.dataSource = dataSource;
 		jdbcTemplate = new JdbcTemplate();
 		jdbcTemplate.setDataSource(dataSource);
 	}

}